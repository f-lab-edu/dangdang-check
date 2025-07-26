package com.dangdang.check.domain.identity;

import com.dangdang.check.common.constant.AuthConstants;
import com.dangdang.check.core.token.JwtService;
import com.dangdang.check.core.token.RefreshTokenCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class LogoutApiServiceImpl implements LogoutApiService {

    private final JwtService jwtService;
    private final RefreshTokenCommandService refreshTokenCommandService;

    @Override
    public void logout(String refreshToken) {
        Assert.hasText(refreshToken, "Refresh token is required");
        jwtService.validateToken(refreshToken, AuthConstants.REFRESH_TOKEN_TYPE);
        refreshTokenCommandService.deleteById(refreshToken);
    }
}