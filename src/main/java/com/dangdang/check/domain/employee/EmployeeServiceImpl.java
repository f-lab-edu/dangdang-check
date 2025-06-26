package com.dangdang.check.domain.employee;

import com.dangdang.check.core.employee.EmployeeCommandService;
import com.dangdang.check.core.employee.EmployeeFindService;
import com.dangdang.check.domain.employee.request.RegisterEmployee;
import com.dangdang.check.domain.employee.request.UpdatePassword;
import com.dangdang.check.domain.employee.request.UpdateProfile;
import com.dangdang.check.domain.employee.response.EmployeeInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    
    private final EmployeeFindService employeeFindService;
    private final EmployeeCommandService employeeCommandService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public EmployeeInfo registerEmployee(RegisterEmployee command) {
        String encodedPassword = passwordEncoder.encode(command.getPassword());
        EmployeeEntity employee = EmployeeEntityFactory.from(command, encodedPassword);
        return EmployeeEntityFactory.to(employeeCommandService.save(employee));
    }

    @Override
    @Transactional
    public EmployeeInfo updateProfile(UpdateProfile command) {
        EmployeeEntity employee = employeeFindService.findByLoginId(command.getLoginId());
        employeeCommandService.updateProfile(employee, command.getName(), command.getNickname());
        return EmployeeEntityFactory.to(employee);
    }

    @Override
    @Transactional
    public EmployeeInfo updatePassword(UpdatePassword command) {
        EmployeeEntity employee = employeeFindService.findByLoginIdAndPassword(command.getLoginId(), passwordEncoder.encode(command.getCurrentPassword()));
        employeeCommandService.updatePassword(employee, passwordEncoder.encode(command.getNewPassword()));
        return EmployeeEntityFactory.to(employee);
    }
}
