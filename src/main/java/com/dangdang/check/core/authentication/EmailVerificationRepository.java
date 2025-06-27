package com.dangdang.check.core.authentication;

import com.dangdang.check.domain.verification.EmailVerification;
import org.springframework.data.repository.CrudRepository;

public interface EmailVerificationRepository extends CrudRepository<EmailVerification, String> {
}
