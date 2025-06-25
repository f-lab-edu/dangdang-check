package com.dangdang.check.interfaces.employee.response;

import com.dangdang.check.domain.employee.EmployeeInfo;
import com.dangdang.check.domain.employee.Role;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RegisterEmployeeResponse {
    private final Long id;
    private final String name;
    private final String nickname;
    private final String email;
    private final String loginId;
    private final String password;
    private final Role role;

    public RegisterEmployeeResponse(EmployeeInfo employeeInfo) {
        this.id = employeeInfo.getId();
        this.name = employeeInfo.getName();
        this.nickname = employeeInfo.getNickname();
        this.email = employeeInfo.getEmail();
        this.loginId = employeeInfo.getLoginId();
        this.password = employeeInfo.getPassword();
        this.role = employeeInfo.getRole();
    }
}
