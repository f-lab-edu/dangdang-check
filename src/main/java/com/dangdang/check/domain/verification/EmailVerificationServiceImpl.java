package com.dangdang.check.domain.verification;

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

    @Override
    @Transactional
    public String createAndSaveCode(String email) {
        String code = CodeGenerator.generateAlphaNumericCode();
        emailVerificationCommandService.save(EmailVerificationFactory.from(email, code));
        return code;
    }
}
