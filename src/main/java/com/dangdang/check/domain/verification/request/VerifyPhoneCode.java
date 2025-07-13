package com.dangdang.check.domain.verification.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class VerifyPhoneCode {
    private final String mobilePhone;
    private final String code;

    @Builder
    public VerifyPhoneCode(String mobilePhone, String code) {
        this.mobilePhone = mobilePhone;
        this.code = code;
    }
}
