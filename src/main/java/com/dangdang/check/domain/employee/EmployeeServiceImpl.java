package com.dangdang.check.domain.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeStore employeeStore;
    private final EmployeeValidator employeeValidator;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public EmployeeInfo registerEmployee(EmployeeCommand.RegisterEmployeeRequest request) {
        employeeValidator.checkRegisterEmployee(request);
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        Employee initEmployee = request.toEntity(encodedPassword);
        Employee employee = employeeStore.storeEmployee(initEmployee);
        return new EmployeeInfo(employee);
    }
}
