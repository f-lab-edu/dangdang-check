package com.dangdang.check.core.grooming;

import com.dangdang.check.domain.grooming.GroomingReservationPetEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GroomingReservationPetCommandService {

    private final GroomingReservationPetJpaRepository groomingReservationPetJpaRepository;

    @Transactional
    public void save(GroomingReservationPetEntity groomingReservationPetEntity) {
        groomingReservationPetJpaRepository.save(groomingReservationPetEntity);
    }
}
