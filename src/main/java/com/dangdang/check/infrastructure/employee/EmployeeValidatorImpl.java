package com.dangdang.check.infrastructure.employee;

import com.dangdang.check.domain.employee.EmployeeCommand;
import com.dangdang.check.domain.employee.EmployeeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeValidatorImpl implements EmployeeValidator {

    @Override
    public void checkRegisterEmployee(EmployeeCommand.RegisterEmployeeRequest request) {

    }
}
