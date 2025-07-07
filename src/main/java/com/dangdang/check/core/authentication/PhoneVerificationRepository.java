package com.dangdang.check.core.authentication;

import com.dangdang.check.domain.verification.PhoneVerification;
import org.springframework.data.repository.CrudRepository;

public interface PhoneVerificationRepository extends CrudRepository<PhoneVerification, String> {

}