package com.dangdang.check.core.store;

import com.dangdang.check.domain.store.BusinessInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessInfoJpaRepository extends JpaRepository<BusinessInfoEntity, Long> {
}
