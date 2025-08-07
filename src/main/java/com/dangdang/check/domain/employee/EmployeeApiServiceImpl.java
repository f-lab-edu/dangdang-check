package com.dangdang.check.domain.employee;

import com.dangdang.check.core.employee.EmployeeCommandService;
import com.dangdang.check.core.employee.EmployeeFindService;
import com.dangdang.check.domain.employee.request.RegisterEmployee;
import com.dangdang.check.domain.employee.request.UpdatePassword;
import com.dangdang.check.domain.employee.request.UpdateProfile;
import com.dangdang.check.domain.employee.response.EmployeeInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeApiServiceImpl implements UserDetailsService, EmployeeApiService {
    
    private final EmployeeFindService employeeFindService;
    private final EmployeeCommandService employeeCommandService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public EmployeeInfo registerEmployee(RegisterEmployee command) {
        String encodedPassword = passwordEncoder.encode(command.getPassword());
        EmployeeEntity employee = EmployeeEntityFactory.from(command, encodedPassword);
        return EmployeeEntityFactory.toInfo(employeeCommandService.save(employee));
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return EmployeeEntityFactory.toDetails(employeeFindService.findByLoginId(username));
    }

    @Override
    @Transactional
    public EmployeeInfo updateProfile(UpdateProfile command) {
        EmployeeEntity employee = employeeFindService.findByLoginId(command.getLoginId());
        employeeCommandService.updateProfile(employee, command.getName(), command.getNickname());
        return EmployeeEntityFactory.toInfo(employee);
    }

    @Override
    @Transactional
    public EmployeeInfo updatePassword(UpdatePassword command) {
        EmployeeEntity employee = employeeFindService.findByLoginId(command.getLoginId());
        validateCurrentPassword(command.getCurrentPassword(), employee.getPassword());
        employeeCommandService.updatePassword(employee, passwordEncoder.encode(command.getNewPassword()));
        return EmployeeEntityFactory.toInfo(employee);
    }

    private void validateCurrentPassword(String currentPassword, String encodedPassword) {
        if (!passwordEncoder.matches(currentPassword, encodedPassword)) {
            throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
        }
    }
}
