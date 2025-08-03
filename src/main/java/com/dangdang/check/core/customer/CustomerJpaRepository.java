package com.dangdang.check.core.customer;

import com.dangdang.check.domain.customer.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Long> {
}
