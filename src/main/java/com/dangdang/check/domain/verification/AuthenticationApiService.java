package com.dangdang.check.domain.verification;

public interface AuthenticationApiService {

    boolean sendVerificationCode(String email);
}
