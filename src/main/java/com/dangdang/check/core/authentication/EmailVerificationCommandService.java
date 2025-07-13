package com.dangdang.check.core.authentication;

import com.dangdang.check.domain.verification.EmailVerification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmailVerificationCommandService {

    private final EmailVerificationRepository emailVerificationRepository;

    @Transactional
    public EmailVerification save(EmailVerification emailVerification) {
        return emailVerificationRepository.save(emailVerification);
    }

    public void delete(EmailVerification emailVerification) {
        emailVerificationRepository.delete(emailVerification);
    }

    public void incrementFailCount(EmailVerification emailVerification) {
        emailVerification.modifyFailCount(emailVerification.getFailCount() + 1);
        emailVerificationRepository.save(emailVerification);
    }
}
