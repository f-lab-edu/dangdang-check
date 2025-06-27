package com.dangdang.check.core.authentication;

import com.dangdang.check.domain.verification.MailVerification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailVerificationCommandService {

    private final MailVerificationRepository mailVerificationRepository;

    public MailVerification save(MailVerification mailVerification) {
        return mailVerificationRepository.save(mailVerification);
    }
}
