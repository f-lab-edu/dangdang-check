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

}
