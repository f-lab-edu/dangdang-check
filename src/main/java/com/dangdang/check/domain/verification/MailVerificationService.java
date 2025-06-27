package com.dangdang.check.domain.verification;

public interface MailVerificationService {

    boolean sendVerificationCode(String email);
}
