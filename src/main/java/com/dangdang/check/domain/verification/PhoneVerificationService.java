package com.dangdang.check.domain.verification;

public interface PhoneVerificationService {

    String createAndSaveCode(String mobilePhone);

    boolean verifyPhoneCode(String mobilePhone, String code);
}
