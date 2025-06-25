package com.dangdang.check.domain.employee;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RegisterEmployee {

    private final String name;
    private final String nickname;
    private final String email;
    private final String loginId;
    private final String password;
    private final String mobilePhone;

    @Builder
    public RegisterEmployee(String name, String nickname, String email, String loginId, String password, String mobilePhone) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.loginId = loginId;
        this.password = password;
        this.mobilePhone = mobilePhone;
    }

}