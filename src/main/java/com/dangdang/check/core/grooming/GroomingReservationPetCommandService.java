package com.dangdang.check.core.grooming;

import com.dangdang.check.domain.grooming.GroomingReservationPetEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GroomingReservationPetCommandService {

    private final GroomingReservationPetJpaRepository groomingReservationPetJpaRepository;

    public void save(GroomingReservationPetEntity groomingReservationPetEntity) {
        groomingReservationPetJpaRepository.save(groomingReservationPetEntity);
    }
}
