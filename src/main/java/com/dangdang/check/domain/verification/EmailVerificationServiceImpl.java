package com.dangdang.check.domain.verification;

import com.dangdang.check.common.constant.VerificationMessageConstants;
import com.dangdang.check.common.util.CodeGenerator;
import com.dangdang.check.core.authentication.EmailVerificationCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailVerificationServiceImpl implements EmailVerificationService {

    private final EmailVerificationCommandService emailVerificationCommandService;
    private final EmailSenderService emailSenderService;

    @Override
    @Transactional
    public boolean sendVerificationCode(String email) {
        String code = CodeGenerator.generateAlphaNumericCode();
        emailVerificationCommandService.save(EmailVerificationFactory.from(email, code));
        emailSenderService.send(email, VerificationMessageConstants.SUBJECT, String.format(VerificationMessageConstants.BODY_TEMPLATE, code))
                .exceptionally(ex -> {
                    log.error("Failed to send email", ex);
                    return false;
                });
        return true;
    }
}
