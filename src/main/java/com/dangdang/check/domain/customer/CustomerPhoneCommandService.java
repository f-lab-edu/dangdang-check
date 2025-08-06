package com.dangdang.check.domain.customer;

import com.dangdang.check.domain.CustomerPhoneJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerPhoneCommandService {

    private final CustomerPhoneJpaRepository customerPhoneJpaRepository;

    public CustomerPhoneEntity save(CustomerPhoneEntity customerPhone) {
        return customerPhoneJpaRepository.save(customerPhone);
    }
}
