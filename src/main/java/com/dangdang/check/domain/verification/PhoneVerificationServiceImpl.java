package com.dangdang.check.domain.verification;

import com.dangdang.check.common.util.CodeGenerator;
import com.dangdang.check.core.authentication.PhoneVerificationCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhoneVerificationServiceImpl implements PhoneVerificationService {

    private final PhoneVerificationCommandService phoneVerificationCommandService;

    @Override
    public String createAndSaveCode(String mobilePhone) {
        String code = CodeGenerator.generateNumericCode();
        phoneVerificationCommandService.save(PhoneVerificationFactory.from(mobilePhone, code));
        return code;
    }

    @Override
    public boolean verifyMobilePhoneCode(String mobilePhone, String code) {
        // init
        return true;
    }

}
