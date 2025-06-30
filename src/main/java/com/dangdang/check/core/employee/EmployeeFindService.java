package com.dangdang.check.core.employee;

import com.dangdang.check.domain.employee.EmployeeEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
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
}