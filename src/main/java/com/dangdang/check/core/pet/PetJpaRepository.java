package com.dangdang.check.core.pet;

import com.dangdang.check.domain.pet.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetJpaRepository extends JpaRepository<PetEntity, Long> {
}
