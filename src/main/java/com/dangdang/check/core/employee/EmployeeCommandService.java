package com.dangdang.check.core.employee;

import com.dangdang.check.domain.employee.EmployeeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeCommandService {

    private final EmployeeJpaRepository employeeJpaRepository;

    public EmployeeEntity save(EmployeeEntity employee) {
        return employeeJpaRepository.save(employee);
    }

    public EmployeeEntity updateProfile(EmployeeEntity employee, String name, String nickname) {
        employee.modifyProfile(name, nickname);
        return employee;
    }
}
