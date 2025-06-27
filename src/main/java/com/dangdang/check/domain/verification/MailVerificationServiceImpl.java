package com.dangdang.check.domain.verification;

import com.dangdang.check.common.constant.VerificationMessageConstants;
import com.dangdang.check.common.util.CodeGenerator;
import com.dangdang.check.core.authentication.MailVerificationCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailVerificationServiceImpl implements MailVerificationService {

    private final MailVerificationCommandService mailVerificationCommandService;
    private final MailSenderService mailSenderService;

    @Override
    @Transactional
    public boolean sendVerificationCode(String email) {
        String code = CodeGenerator.generateAlphaNumericCode();
        mailVerificationCommandService.save(MailVerificationFactory.from(email, code));
        mailSenderService.send(email, VerificationMessageConstants.SUBJECT, String.format(VerificationMessageConstants.BODY_TEMPLATE, code))
                .exceptionally(ex -> {
                    log.error("Failed to send email", ex);
                    return false;
                });
        return true;
    }
}
