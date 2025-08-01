package com.dangdang.check.core.grooming;

import com.dangdang.check.domain.grooming.GroomingReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface GroomingReservationJpaRepository extends JpaRepository<GroomingReservationEntity, Long> {

    @Query("""
            SELECT COUNT(rp) > 0
            FROM GroomingReservationPetEntity rp
            JOIN rp.groomingReservation gr
            WHERE rp.pet.id IN :petIds
              AND gr.startAt < :endAt
              AND gr.endAt > :startAt
            """)
    boolean existsOverlappingReservation(@Param("petIds") List<Long> petIds,
                                         @Param("startAt") LocalDateTime startAt,
                                         @Param("endAt") LocalDateTime endAt);
}
