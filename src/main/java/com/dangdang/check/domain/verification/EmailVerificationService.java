package com.dangdang.check.domain.verification;

public interface EmailVerificationService {

    String createAndSaveCode(String email);
}
