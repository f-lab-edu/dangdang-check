package com.dangdang.check.domain.verification;

public interface PhoneVerificationService {

    String createAndSaveCode(String mobilePhone);

    boolean verifyMobilePhoneCode(String mobilePhone, String code);
}
