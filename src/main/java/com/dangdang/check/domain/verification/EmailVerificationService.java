package com.dangdang.check.domain.verification;

public interface EmailVerificationService {

    boolean sendVerificationCode(String email);
}
