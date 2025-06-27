package com.dangdang.check.domain.verification;

public class MailVerificationFactory {

    public static MailVerification from(String email, String code) {
        return new MailVerification(email, code);
    }
}