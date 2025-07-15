package com.dangdang.check.core.store;

import com.dangdang.check.domain.store.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreJpaRepository extends JpaRepository<StoreEntity, Long> {
}
