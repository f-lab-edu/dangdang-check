package com.dangdang.check.domain.verification;

import com.dangdang.check.domain.verification.request.VerifyPhoneCode;

public interface PhoneAuthenticationApiService {

    void sendVerificationCode(String mobilePhone);
    boolean verifyCode(VerifyPhoneCode command);
}
