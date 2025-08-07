package com.dangdang.check.core.customer;

import com.dangdang.check.domain.customer.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerCommandService {

    private final CustomerJpaRepository customerJpaRepository;

    public CustomerEntity save(CustomerEntity customer) {
        return customerJpaRepository.save(customer);
    }
}
