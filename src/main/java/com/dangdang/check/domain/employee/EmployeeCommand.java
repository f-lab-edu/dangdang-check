package com.dangdang.check.domain.employee;

import lombok.Getter;
import lombok.ToString;

public class EmployeeCommand {

    @Getter
    @ToString
    public static class RegisterEmployeeRequest {

        private final String name;
        private final String nickname;
        private final String email;
        private final String loginId;
        private final String password;
        private final String mobilePhone;

        public RegisterEmployeeRequest(String name, String nickname, String email, String loginId, String password, String mobilePhone) {
            this.name = name;
            this.nickname = nickname;
            this.email = email;
            this.loginId = loginId;
            this.password = password;
            this.mobilePhone = mobilePhone;
        }

        public Employee toEntity(String encodedPassword) {
            return Employee.builder()
                    .name(name)
                    .nickname(nickname)
                    .email(email)
                    .loginId(loginId)
                    .password(encodedPassword)
                    .mobilePhone(mobilePhone)
                    .build();
        }

    }
}
