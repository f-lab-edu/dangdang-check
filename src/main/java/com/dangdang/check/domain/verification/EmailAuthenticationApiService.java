package com.dangdang.check.domain.verification;

import com.dangdang.check.domain.verification.request.VerifyEmailCode;

public interface EmailAuthenticationApiService {

    boolean sendVerificationCode(String email);
    boolean verifyCode(VerifyEmailCode command);
}
