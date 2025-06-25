package com.dangdang.check.domain.employee;

import com.dangdang.check.core.employee.EmployeeCommandService;
import com.dangdang.check.domain.employee.request.RegisterEmployee;
import com.dangdang.check.domain.employee.request.UpdateProfile;
import com.dangdang.check.domain.employee.response.EmployeeInfo;
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

    @Override
    public EmployeeInfo updateProfile(UpdateProfile command) {
        return null;
    }
}
