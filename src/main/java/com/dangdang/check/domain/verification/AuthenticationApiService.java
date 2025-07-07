package com.dangdang.check.domain.verification;

import com.dangdang.check.domain.verification.request.VerifyEmailCode;

public interface AuthenticationApiService {

    boolean sendVerificationCode(String email);
    boolean verifyEmailCode(VerifyEmailCode command);
}
