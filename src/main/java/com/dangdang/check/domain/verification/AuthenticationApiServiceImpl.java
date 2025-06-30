package com.dangdang.check.domain.verification;

import com.dangdang.check.common.constant.VerificationMessageConstants;
import com.dangdang.check.core.employee.EmployeeFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationApiServiceImpl implements AuthenticationApiService {

    private final EmployeeFindService employeeFindService;
    private final EmailVerificationService emailVerificationService;
    private final EmailSenderService emailSenderService;

    @Override
    public boolean sendVerificationCode(String email) {
        validateEmailIsAvailable(email);
        String code = emailVerificationService.createAndSaveCode(email);
        emailSenderService.send(email, VerificationMessageConstants.SUBJECT, String.format(VerificationMessageConstants.BODY_TEMPLATE, code))
                .exceptionally(ex -> {
                    log.error("Failed to send email", ex);
                    return false;
                });
        return true;
    }
    private void validateEmailIsAvailable(String email) {
        if (employeeFindService.existsByEmail(email)) {
            throw new RuntimeException("이미 존재하는 email입니다.");
        }
    }
}
