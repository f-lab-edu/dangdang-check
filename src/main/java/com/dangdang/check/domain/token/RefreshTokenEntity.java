package com.dangdang.check.domain.token;

import com.dangdang.check.common.constant.AuthConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.util.StringUtils;

import java.security.InvalidParameterException;

@Getter
@NoArgsConstructor
@RedisHash(value = "refreshToken", timeToLive = AuthConstants.REFRESH_TOKEN_TTL_MS)
public class RefreshTokenEntity {

    @Id
    private String refreshToken;

    private String loginId;

    public RefreshTokenEntity(String refreshToken, String loginId) {
        if (isNotValidParams(refreshToken, loginId)) {
            throw new InvalidParameterException();
        }

        this.refreshToken = refreshToken;
        this.loginId = loginId;
    }

    private boolean isNotValidParams(String refreshToken, String loginId) {
        return !StringUtils.hasText(refreshToken) || !StringUtils.hasText(loginId);
    }
}