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
    private String loginId;

    private String refreshToken;

    public RefreshTokenEntity(String loginId, String refreshToken) {
        if (isNotValidParams(loginId, refreshToken)) {
            throw new InvalidParameterException();
        }

        this.loginId = loginId;
        this.refreshToken = refreshToken;
    }

    private boolean isNotValidParams(String loginId, String refreshToken) {
        return !StringUtils.hasText(loginId)
                || !StringUtils.hasText(refreshToken);
    }
}