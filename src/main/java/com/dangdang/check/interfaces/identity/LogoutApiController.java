package com.dangdang.check.interfaces.identity;

import com.dangdang.check.common.constant.AuthConstants;
import com.dangdang.check.domain.identity.LogoutApiService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/logout")
@RequiredArgsConstructor
public class LogoutApiController {

    private final LogoutApiService logoutApiService;

    @PostMapping
    public void logout(@CookieValue(value = AuthConstants.REFRESH_TOKEN_TYPE, required = false) String refreshToken, HttpServletResponse response) {
        logoutApiService.logout(refreshToken);
        expireRefreshTokenCookie(response);
    }

    private void expireRefreshTokenCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(AuthConstants.REFRESH_TOKEN_TYPE, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }
}