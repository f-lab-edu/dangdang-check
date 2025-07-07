package com.dangdang.check.domain.verification;

import com.dangdang.check.common.util.CodeGenerator;
import com.dangdang.check.core.authentication.EmailVerificationCommandService;
import com.dangdang.check.core.authentication.EmailVerificationFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.dangdang.check.common.constant.VerificationMessageConstants.MAX_FAIL_COUNT;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailVerificationServiceImpl implements EmailVerificationService {

    private final EmailVerificationCommandService emailVerificationCommandService;
    private final EmailVerificationFindService emailVerificationFindService;

    @Override
    @Transactional
    public String createAndSaveCode(String email) {
        String code = CodeGenerator.generateAlphaNumericCode();
        emailVerificationCommandService.save(EmailVerificationFactory.from(email, code));
        return code;
    }

    @Override
    public boolean verifyEmailCode(String email, String code) {
        EmailVerification emailVerification = emailVerificationFindService.findById(email);
        validateFailCountLimit(emailVerification);
        validateVerificationCode(code, emailVerification);
        emailVerificationCommandService.delete(emailVerification);
        return true;
    }

    private void validateVerificationCode(String code, EmailVerification emailVerification) {
        if (!emailVerification.getCode().equals(code)) {
            emailVerificationCommandService.incrementFailCount(emailVerification);
            throw new RuntimeException("인증 코드가 일치하지 않습니다.");

        }
    }

    private void validateFailCountLimit(EmailVerification emailVerification) {
        if (emailVerification.getFailCount() >= MAX_FAIL_COUNT) {
            emailVerificationCommandService.delete(emailVerification);
            throw new RuntimeException("인증 코드가 5회 이상 실패하여 더 이상 인증할 수 없습니다.");
        }
    }
}
