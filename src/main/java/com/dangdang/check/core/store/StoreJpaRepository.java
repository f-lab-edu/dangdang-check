package com.dangdang.check.core.store;

import com.dangdang.check.domain.store.StoreEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreJpaRepository extends JpaRepository<StoreEntity, Long> {

    @EntityGraph(attributePaths = {"businessInfo"})
    Optional<StoreEntity> findById(Long id);
}
