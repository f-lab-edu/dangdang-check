package com.dangdang.check.core.auth;

import com.dangdang.check.domain.auth.RefreshTokenEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenCommandService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenEntity save(RefreshTokenEntity refreshToken) {
        return refreshTokenRepository.save(refreshToken);
    }

}
