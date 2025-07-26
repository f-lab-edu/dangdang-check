package com.dangdang.check.core.token;

import com.dangdang.check.domain.token.RefreshTokenEntity;
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
