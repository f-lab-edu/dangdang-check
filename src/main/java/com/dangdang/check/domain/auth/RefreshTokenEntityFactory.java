package com.dangdang.check.domain.auth;

public class RefreshTokenEntityFactory {

    public static RefreshTokenEntity of(String loginId, String refreshToken) {
        return new RefreshTokenEntity(loginId, refreshToken);
    }

}
