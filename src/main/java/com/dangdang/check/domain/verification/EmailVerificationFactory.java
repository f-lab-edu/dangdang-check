package com.dangdang.check.domain.verification;

public class EmailVerificationFactory {

    public static EmailVerification from(String email, String code) {
        return new EmailVerification(email, code);
    }
}