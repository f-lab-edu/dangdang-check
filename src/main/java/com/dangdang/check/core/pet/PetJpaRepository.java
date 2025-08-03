package com.dangdang.check.core.pet;

import com.dangdang.check.domain.pet.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface PetJpaRepository extends JpaRepository<PetEntity, Long> {

    List<PetEntity> findAllByIdInAndIsDeletedFalse(Collection<Long> ids);
}
