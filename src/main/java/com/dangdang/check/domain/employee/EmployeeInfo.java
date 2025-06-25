package com.dangdang.check.domain.employee;

import lombok.Builder;
import lombok.Getter;

@Getter
public class EmployeeInfo {

    private final Long id;
    private final String name;
    private final String nickname;
    private final String email;
    private final String loginId;
    private final Role role;

    @Builder
    public EmployeeInfo(Long id, String name, String nickname, String email, String loginId, Role role) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.loginId = loginId;
        this.role = role;
    }
}
