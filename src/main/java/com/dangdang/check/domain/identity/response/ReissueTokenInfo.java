package com.dangdang.check.domain.identity.response;

import lombok.Getter;

@Getter
public class ReissueTokenInfo {

    private final String accessToken;
    private final String refreshToken;

    public ReissueTokenInfo(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
