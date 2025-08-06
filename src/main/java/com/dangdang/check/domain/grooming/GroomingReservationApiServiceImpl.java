package com.dangdang.check.domain.grooming;

import com.dangdang.check.core.employee.EmployeeFindService;
import com.dangdang.check.core.grooming.GroomingReservationCommandService;
import com.dangdang.check.core.grooming.GroomingReservationFindService;
import com.dangdang.check.core.grooming.GroomingReservationPetCommandService;
import com.dangdang.check.core.pet.PetFindService;
import com.dangdang.check.domain.customer.CustomerEntity;
import com.dangdang.check.domain.grooming.request.RegisterGroomingReservation;
import com.dangdang.check.domain.grooming.response.GroomingReservationInfo;
import com.dangdang.check.domain.pet.PetEntity;
import com.dangdang.check.domain.store.StoreEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroomingReservationApiServiceImpl implements GroomingReservationApiService {

    private final GroomingReservationCommandService groomingReservationCommandService;
    private final GroomingReservationFindService groomingReservationFindService;
    private final GroomingReservationPetCommandService groomingReservationPetCommandService;
    private final EmployeeFindService employeeFindService;
    private final PetFindService petFindService;

    @Override
    @Transactional
    public GroomingReservationInfo registerGroomingReservation(RegisterGroomingReservation command) {
        List<PetEntity> pets = petFindService.findAllById(new HashSet<>(command.getPetIds()));
        CustomerEntity customer = pets.getFirst().getCustomer();
        validateRegisterGroomingReservation(command, customer, employeeFindService.findByLoginId(command.getEmployeeLoginId()).getStore());
        GroomingReservationEntity groomingReservation = groomingReservationCommandService.save(GroomingReservationEntityFactory.from(command, customer));
        List<GroomingReservationPetEntity> groomingReservationPets = new ArrayList<>();
        for (PetEntity pet : pets) {
            GroomingReservationPetEntity groomingReservationPet = GroomingReservationPetEntityFactory.from(groomingReservation, pet);
            groomingReservation.addGroomingReservationPet(groomingReservationPet);
            groomingReservationPets.add(groomingReservationPet);
        }
        groomingReservationPetCommandService.saveAll(groomingReservationPets);
        return new GroomingReservationInfo(groomingReservation);
    }

    private void validateRegisterGroomingReservation(RegisterGroomingReservation command, CustomerEntity customer, StoreEntity store) {
        store.assertAvailable();
        if (customer == null || customer.isDeleted()) {
            throw new IllegalArgumentException("삭제된 고객은 예약할 수 없습니다.");
        }

        if (!command.getStartAt().isBefore(command.getEndAt())) {
            throw new IllegalArgumentException("시작 시간은 종료 시간보다 빨라야 합니다.");
        }

        if (command.getStartAt().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("과거 시간으로 예약할 수 없습니다.");
        }
        if (!customer.getStore().equals(store)) {
            throw new IllegalStateException("고객의 매장과 직원의 매장이 일치하지 않습니다.");
        }

        if (groomingReservationFindService.existsOverlappingReservation(command.getPetIds(), command.getStartAt(), command.getEndAt())) {
            throw new IllegalStateException("해당 시간대에 이미 예약이 존재합니다.");
        }

    }
}
