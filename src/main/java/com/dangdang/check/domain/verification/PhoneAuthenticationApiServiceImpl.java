package com.dangdang.check.domain.verification;

import com.dangdang.check.common.constant.VerificationMessageConstants;
import com.dangdang.check.core.employee.EmployeeFindService;
import com.dangdang.check.domain.verification.request.VerifyPhoneCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhoneAuthenticationApiServiceImpl implements PhoneAuthenticationApiService {

    private final EmployeeFindService employeeFindService;
    private final PhoneVerificationService phoneVerificationService;
    private final PhoneSenderService phoneSenderService;

    @Override
    public boolean sendVerificationCode(String mobilePhone) {
        validateMobilePhoneIsAvailable(mobilePhone);
        String code = phoneVerificationService.createAndSaveCode(mobilePhone);
        phoneSenderService.send(mobilePhone, String.format(VerificationMessageConstants.BODY_TEMPLATE, code))
                .exceptionally(ex -> {
                    log.error("Failed to send sms", ex);
                    return false;
                });
        return true;
    }

    @Override
    public boolean verifyCode(VerifyPhoneCode command) {
        return phoneVerificationService.verifyPhoneCode(command.getMobilePhone(), command.getCode());
    }

    private void validateMobilePhoneIsAvailable(String mobilePhone) {
        if (employeeFindService.existsByMobilePhone(mobilePhone)) {
            throw new RuntimeException("이미 존재하는 mobilePhone입니다.");
        }
    }
}
