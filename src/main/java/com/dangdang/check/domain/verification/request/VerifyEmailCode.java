package com.dangdang.check.domain.verification.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class VerifyEmailCode {
    private final String email;
    private final String code;

    @Builder
    public VerifyEmailCode(String email, String code) {
        this.email = email;
        this.code = code;
    }
}
