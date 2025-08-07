package com.dangdang.check.core.grooming;

import com.dangdang.check.domain.grooming.GroomingReservationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class GroomingReservationCommandService {

    private final GroomingReservationJpaRepository groomingReservationJpaRepository;

    public GroomingReservationEntity save(GroomingReservationEntity groomingReservation) {
        return groomingReservationJpaRepository.save(groomingReservation);
    }
}
