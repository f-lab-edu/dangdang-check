package com.dangdang.check.core.auth;

import com.dangdang.check.common.constant.AuthConstants;
import com.dangdang.check.common.exception.JwtValidationException;
import com.dangdang.check.common.util.JwtUtil;
import com.dangdang.check.domain.employee.Role;
import com.dangdang.check.domain.employee.response.EmployeeDetails;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtUtil jwtUtil;

    public EmployeeDetails getEmployeeDetails(String token) {
        try {
            jwtUtil.isExpired(token);
        } catch (ExpiredJwtException e) {
            throw new JwtValidationException("access token expired", e);
        }

        if (isInvalidAccessToken(token)) {
            throw new JwtValidationException("invalid access token");
        }

        String loginId = jwtUtil.getLoginId(token);
        String role = jwtUtil.getRole(token);
        return new EmployeeDetails(loginId, null, Role.valueOf(role));
    }


    public String createAccessToken(String loginId, String role) {
        return jwtUtil.createJwt(AuthConstants.ACCESS_TOKEN_TYPE, loginId, role, AuthConstants.ACCESS_TOKEN_TTL_MS);
    }

    public String createRefreshToken(String loginId, String role) {
        return jwtUtil.createJwt(AuthConstants.REFRESH_TOKEN_TYPE, loginId, role, AuthConstants.REFRESH_TOKEN_TTL_MS);
    }

    private boolean isInvalidAccessToken(String token) {
        return AuthConstants.ACCESS_TOKEN_TYPE.equals(jwtUtil.getCategory(token));
    }
}