package com.dangdang.check.core.grooming;

import com.dangdang.check.domain.grooming.GroomingReservationPetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroomingReservationPetJpaRepository extends JpaRepository<GroomingReservationPetEntity, Long> {

}
