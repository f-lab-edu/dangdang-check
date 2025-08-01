package com.dangdang.check.core.grooming;

import com.dangdang.check.domain.grooming.GroomingReservationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroomingReservationCommandService {

    private final GroomingReservationJpaRepository groomingReservationJpaRepository;

    @Transactional
    public GroomingReservationEntity save(GroomingReservationEntity groomingReservation) {
        return groomingReservationJpaRepository.save(groomingReservation);
    }

    @Transactional(readOnly = true)
    public boolean existsOverlappingReservation(List<Long> petIds, LocalDateTime startAt, LocalDateTime endAt) {
        return groomingReservationJpaRepository.existsOverlappingReservation(petIds, startAt, endAt);
    }
}
