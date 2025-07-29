package com.dangdang.check.interfaces.identity;

import com.dangdang.check.common.constant.AuthConstants;
import com.dangdang.check.domain.identity.ReissueApiService;
import com.dangdang.check.domain.identity.response.ReissueTokenInfo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReissueApiController {

    private final ReissueApiService reissueApiService;

    @PostMapping("/api/reissue")
    public void reissue(@CookieValue(value = AuthConstants.REFRESH_TOKEN_TYPE, required = false) String refreshToken,
                        HttpServletResponse response) {
        ReissueTokenInfo reissueTokenInfo = reissueApiService.reissueToken(refreshToken);
        response.setHeader(AuthConstants.ACCESS_TOKEN_TYPE, reissueTokenInfo.getAccessToken());
        response.addCookie(createCookie(AuthConstants.REFRESH_TOKEN_TYPE, reissueTokenInfo.getRefreshToken()));
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge((int) AuthConstants.REFRESH_TOKEN_TTL_MS);
        cookie.setHttpOnly(true);
        return cookie;
    }
}
