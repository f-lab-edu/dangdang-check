package com.dangdang.check.domain.verification;

public class PhoneVerificationFactory {
    public static PhoneVerification from(String mobilePhone, String code) {
        return new PhoneVerification(mobilePhone, code);
    }
}
