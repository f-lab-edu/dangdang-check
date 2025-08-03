package com.dangdang.check.core.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenFindService {

    private final RefreshTokenRepository refreshTokenRepository;

    public boolean existsById(String id) {
        return refreshTokenRepository.existsById(id);
    }
}
