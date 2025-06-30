package com.dangdang.check.core.employee;

import com.dangdang.check.domain.employee.EmployeeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeCommandService {

    private final EmployeeJpaRepository employeeJpaRepository;

    @Transactional
    public EmployeeEntity save(EmployeeEntity employee) {
        return employeeJpaRepository.save(employee);
    }

    @Transactional
    public EmployeeEntity updateProfile(EmployeeEntity employee, String name, String nickname) {
        employee.modifyProfile(name, nickname);
        return employee;
    }

    @Transactional
    public EmployeeEntity updatePassword(EmployeeEntity employee, String password) {
        employee.modifyPassword(password);
        return employee;
    }
}
