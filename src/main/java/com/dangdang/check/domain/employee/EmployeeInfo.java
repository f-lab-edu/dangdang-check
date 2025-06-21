package com.dangdang.check.domain.employee;

import lombok.Getter;

@Getter
public class EmployeeInfo {

    private final Long id;
    private final String name;
    private final String nickname;
    private final String email;
    private final String loginId;
    private final String password;
    private final Role role;

    public EmployeeInfo(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.nickname = employee.getNickname();
        this.email = employee.getEmail();
        this.loginId = employee.getLoginId();
        this.password = employee.getPassword();
        this.role = employee.getRole();
    }
}
