package com.dangdang.check.domain;

import com.dangdang.check.domain.customer.CustomerPhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerPhoneJpaRepository extends JpaRepository<CustomerPhoneEntity, Long> {
}
