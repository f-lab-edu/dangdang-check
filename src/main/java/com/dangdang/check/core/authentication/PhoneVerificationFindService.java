package com.dangdang.check.core.authentication;

import com.dangdang.check.domain.verification.PhoneVerification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhoneVerificationFindService {

    private final PhoneVerificationRepository phoneVerificationRepository;

    public PhoneVerification findById(String id) {
        return phoneVerificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Phone verification not found for id: " + id));
    }
}
