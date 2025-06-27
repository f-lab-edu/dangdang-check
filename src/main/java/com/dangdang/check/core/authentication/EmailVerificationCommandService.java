package com.dangdang.check.core.authentication;

import com.dangdang.check.domain.verification.EmailVerification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailVerificationCommandService {

    private final EmailVerificationRepository emailVerificationRepository;

    public EmailVerification save(EmailVerification emailVerification) {
        return emailVerificationRepository.save(emailVerification);
    }
}
