package com.dangdang.check.core.authentication;

import com.dangdang.check.domain.verification.EmailVerification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailVerificationFindService {

    private final EmailVerificationRepository emailVerificationRepository;

    public EmailVerification findById(String id) {
        return emailVerificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Email verification not found for id: " + id));
    }

}
