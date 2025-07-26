package com.dangdang.check.core.auth;

import com.dangdang.check.domain.auth.RefreshTokenEntity;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshTokenEntity, String> {
}
