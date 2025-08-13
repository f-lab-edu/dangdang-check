package com.dangdang.check.core.grooming;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GroomingReservationFindService {

    private final GroomingReservationJpaRepository groomingReservationJpaRepository;

    public boolean existsOverlappingReservation(List<Long> petIds, LocalDateTime startAt, LocalDateTime endAt) {
        return groomingReservationJpaRepository.existsOverlappingReservation(petIds, startAt, endAt);
    }
}
