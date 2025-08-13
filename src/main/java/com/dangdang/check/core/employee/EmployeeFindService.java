package com.dangdang.check.core.employee;

import com.dangdang.check.domain.employee.EmployeeEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmployeeFindService {

    private final EmployeeJpaRepository employeeJpaRepository;

    public EmployeeEntity findByLoginId(String loginId) {
        return employeeJpaRepository.findByLoginIdAndIsDeletedFalse(loginId)
                .orElseThrow((EntityNotFoundException::new));
    }

    public boolean existsByEmail(String email) {
        return employeeJpaRepository.existsByEmail(email);
    }

    public boolean existsByMobilePhone(String mobilePhone) {
        return employeeJpaRepository.existsByMobilePhone(mobilePhone);
    }
}