package com.dangdang.check.domain.identity;

import com.dangdang.check.common.constant.AuthConstants;
import com.dangdang.check.core.token.JwtService;
import com.dangdang.check.core.token.RefreshTokenCommandService;
import com.dangdang.check.core.token.RefreshTokenFindService;
import com.dangdang.check.domain.identity.response.ReissueTokenInfo;
import com.dangdang.check.domain.token.RefreshTokenEntityFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class ReissueApiServiceImpl implements ReissueApiService {

    private final JwtService jwtService;
    private final RefreshTokenCommandService refreshTokenCommandService;
    private final RefreshTokenFindService refreshTokenFindService;

    @Override
    public ReissueTokenInfo reissueToken(String refreshToken) {
        validateRefreshToken(refreshToken);

        String loginId = jwtService.getLoginId(refreshToken);
        String role = jwtService.getRole(refreshToken);

        String newAccessToken = jwtService.createAccessToken(loginId, role);
        String newRefreshToken = jwtService.createRefreshToken(loginId, role);

        refreshTokenCommandService.deleteById(refreshToken);
        refreshTokenCommandService.save(RefreshTokenEntityFactory.of(newRefreshToken, loginId));

        return new ReissueTokenInfo(newAccessToken, newRefreshToken);
    }

    private void validateRefreshToken(String refreshToken) {
        Assert.hasText(refreshToken, "Refresh token is required");
        jwtService.validateToken(refreshToken, AuthConstants.REFRESH_TOKEN_TYPE);
        if (!refreshTokenFindService.existsById(refreshToken)) {
            throw new RuntimeException("Refresh token does not exist");
        }
    }
}
