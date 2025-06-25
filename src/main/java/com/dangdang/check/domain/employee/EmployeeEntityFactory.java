package com.dangdang.check.domain.employee;

import com.dangdang.check.domain.employee.request.RegisterEmployee;
import com.dangdang.check.domain.employee.response.EmployeeInfo;

public class EmployeeEntityFactory {

    public static EmployeeEntity from(RegisterEmployee command, String encodedPassword) {
        return EmployeeEntity.builder()
                .name(command.getName())
                .nickname(command.getNickname())
                .email(command.getEmail())
                .loginId(command.getLoginId())
                .password(encodedPassword)
                .mobilePhone(command.getMobilePhone())
                .build();
    }

    public static EmployeeInfo to(EmployeeEntity employeeEntity) {
        return EmployeeInfo.builder()
                .id(employeeEntity.getId())
                .name(employeeEntity.getName())
                .nickname(employeeEntity.getNickname())
                .email(employeeEntity.getEmail())
                .loginId(employeeEntity.getLoginId())
                .role(employeeEntity.getRole())
                .build();
    }
}
