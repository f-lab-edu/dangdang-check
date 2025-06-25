package com.dangdang.check.domain.employee;

import com.dangdang.check.core.employee.EmployeeCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeCommandService employeeCommandService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public EmployeeInfo registerEmployee(RegisterEmployee command) {
        String encodedPassword = passwordEncoder.encode(command.getPassword());
        EmployeeEntity initEmployee = EmployeeEntityFactory.from(command, encodedPassword);
        return EmployeeEntityFactory.to(employeeCommandService.save(initEmployee));
    }
}
