package com.dangdang.check.core.auth;

import com.dangdang.check.common.constant.AuthConstants;
import com.dangdang.check.common.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtUtil jwtUtil;

    public String createAccessToken(String loginId, String role) {
        return jwtUtil.createJwt(AuthConstants.ACCESS_TOKEN_TYPE, loginId, role, AuthConstants.ACCESS_TOKEN_TTL_MS);
    }

    public String createRefreshToken(String loginId, String role) {
        return jwtUtil.createJwt(AuthConstants.REFRESH_TOKEN_TYPE, loginId, role, AuthConstants.REFRESH_TOKEN_TTL_MS);
    }
}