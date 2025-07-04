package com.dangdang.check.domain.employee.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpdatePassword {

    private final String loginId;
    private final String currentPassword;
    private final String newPassword;

    @Builder
    public UpdatePassword(String loginId, String currentPassword, String newPassword) {
        this.loginId = loginId;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }
}