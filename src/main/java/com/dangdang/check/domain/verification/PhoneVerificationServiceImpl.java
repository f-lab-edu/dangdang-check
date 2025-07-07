package com.dangdang.check.domain.verification;

import com.dangdang.check.common.util.CodeGenerator;
import com.dangdang.check.core.authentication.PhoneVerificationCommandService;
import com.dangdang.check.core.authentication.PhoneVerificationFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.dangdang.check.common.constant.VerificationMessageConstants.MAX_FAIL_COUNT;

@Service
@RequiredArgsConstructor
public class PhoneVerificationServiceImpl implements PhoneVerificationService {

    private final PhoneVerificationCommandService phoneVerificationCommandService;
    private final PhoneVerificationFindService phoneVerificationFindService;

    @Override
    public String createAndSaveCode(String mobilePhone) {
        String code = CodeGenerator.generateNumericCode();
        phoneVerificationCommandService.save(PhoneVerificationFactory.from(mobilePhone, code));
        return code;
    }

    @Override
    public boolean verifyPhoneCode(String mobilePhone, String code) {
        PhoneVerification phoneVerification = phoneVerificationFindService.findById(mobilePhone);
        validateFailCountLimit(phoneVerification);
        validateVerificationCode(code, phoneVerification);
        phoneVerificationCommandService.delete(phoneVerification);
        return true;
    }

    private void validateVerificationCode(String code, PhoneVerification phoneVerification) {
        if (!phoneVerification.getCode().equals(code)) {
            phoneVerificationCommandService.incrementFailCount(phoneVerification);
            throw new RuntimeException("인증 코드가 일치하지 않습니다.");

        }
    }

    private void validateFailCountLimit(PhoneVerification phoneVerification) {
        if (phoneVerification.getFailCount() >= MAX_FAIL_COUNT) {
            phoneVerificationCommandService.delete(phoneVerification);
            throw new RuntimeException("인증 코드가 5회 이상 실패하여 더 이상 인증할 수 없습니다.");
        }
    }

}
