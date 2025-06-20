package com.dangdang.check.domain.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeValidator employeeValidator;

    @Override
    public EmployeeInfo registerEmployee(EmployeeCommand.RegisterEmployeeRequest request) {
        return null;
    }
}
