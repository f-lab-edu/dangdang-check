package com.dangdang.check.common.filter;

import com.dangdang.check.core.token.JwtService;
import com.dangdang.check.domain.employee.response.EmployeeDetails;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authToken = request.getHeader("Authorization");
        if (isInvalidBearerToken(authToken)) {
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = authToken.substring(7);

        try {
            EmployeeDetails employeeDetails = jwtService.getEmployeeDetails(accessToken);
            SecurityContextHolder
                    .getContext()
                    .setAuthentication(new UsernamePasswordAuthenticationToken(employeeDetails, null, employeeDetails.getAuthorities()));
        } catch (ExpiredJwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);

    }

    private boolean isInvalidBearerToken(String token) {
        return !StringUtils.hasText(token) || !token.startsWith("Bearer ");
    }
}