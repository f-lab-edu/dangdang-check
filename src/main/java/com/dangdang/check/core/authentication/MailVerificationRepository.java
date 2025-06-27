package com.dangdang.check.core.authentication;

import com.dangdang.check.domain.verification.MailVerification;
import org.springframework.data.repository.CrudRepository;

public interface MailVerificationRepository extends CrudRepository<MailVerification, String> {
}
