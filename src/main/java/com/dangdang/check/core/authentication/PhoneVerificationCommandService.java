package com.dangdang.check.core.authentication;

import com.dangdang.check.domain.verification.PhoneVerification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhoneVerificationCommandService {

    private final PhoneVerificationRepository phoneVerificationRepository;

    public PhoneVerification save(PhoneVerification phoneVerification) {
        return phoneVerificationRepository.save(phoneVerification);
    }

    public void delete(PhoneVerification phoneVerification) {
        phoneVerificationRepository.delete(phoneVerification);
    }

    public void incrementFailCount(PhoneVerification phoneVerification) {
        phoneVerification.modifyFailCount(phoneVerification.getFailCount() + 1);
        phoneVerificationRepository.save(phoneVerification);
    }

}
