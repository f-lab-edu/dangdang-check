package com.dangdang.check.core.token;

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

    public void validateToken(String token, String category) {
        if (!category.equals(jwtUtil.getCategory(token))) {
            throw new JwtValidationException("invalid token");
        }

        try {
            jwtUtil.isExpired(token);
        } catch (ExpiredJwtException e) {
            throw new JwtValidationException("token expired", e);
        }
    }

    public EmployeeDetails getEmployeeDetails(String token) {

        String loginId = jwtUtil.getLoginId(token);
        String role = jwtUtil.getRole(token);
        return new EmployeeDetails(loginId, null, Role.valueOf(role));
    }

    public String getLoginId(String token) {
        return jwtUtil.getLoginId(token);
    }

    public String getRole(String token) {
        return jwtUtil.getRole(token);
    }


    public String createAccessToken(String loginId, String role) {
        return jwtUtil.createJwt(AuthConstants.ACCESS_TOKEN_TYPE, loginId, role, AuthConstants.ACCESS_TOKEN_TTL_MS);
    }

    public String createRefreshToken(String loginId, String role) {
        return jwtUtil.createJwt(AuthConstants.REFRESH_TOKEN_TYPE, loginId, role, AuthConstants.REFRESH_TOKEN_TTL_MS);
    }

    private boolean isInvalidAccessToken(String token) {
        return !AuthConstants.ACCESS_TOKEN_TYPE.equals(jwtUtil.getCategory(token));
    }
}