package com.dangdang.check.common.filter;

import com.dangdang.check.common.constant.AuthConstants;
import com.dangdang.check.core.token.JwtService;
import com.dangdang.check.core.token.RefreshTokenCommandService;
import com.dangdang.check.domain.token.RefreshTokenEntityFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final RefreshTokenCommandService refreshTokenCommandService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;



    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Map.Entry<String, String> credentials = extractCredentials(request);
        if (credentials == null) {
            throw new AuthenticationException("Invalid login request") {
            };
        }
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getKey(), credentials.getValue(), null));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        String loginId = authentication.getName();
        String role = authentication.getAuthorities()
                .iterator()
                .next()
                .getAuthority();

        String accessToken = jwtService.createAccessToken(loginId, role);
        String refreshToken = jwtService.createRefreshToken(loginId, role);

        refreshTokenCommandService.save(RefreshTokenEntityFactory.of(refreshToken, loginId));

        response.setHeader(AuthConstants.ACCESS_TOKEN_TYPE, accessToken);
        response.addCookie(createCookie(AuthConstants.REFRESH_TOKEN_TYPE, refreshToken));
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(401);
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge((int) AuthConstants.REFRESH_TOKEN_TTL_MS);
        cookie.setHttpOnly(true);
        return cookie;
    }


    private Map.Entry<String, String> extractCredentials(HttpServletRequest request) {
        try {
            JsonNode jsonNode = objectMapper.readTree(request.getInputStream());
            String loginId = jsonNode.has("loginId") ? jsonNode.get("loginId").asText() : null;
            String password = jsonNode.has("password") ? jsonNode.get("password").asText() : null;

            if (loginId == null || password == null) {
                return null;
            }

            return Map.entry(loginId, password);
        } catch (IOException e) {
            throw new AuthenticationException("Failed to parse JSON request") {
            };
        }
    }
}
