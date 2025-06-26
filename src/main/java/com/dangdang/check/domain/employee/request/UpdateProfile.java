package com.dangdang.check.domain.employee.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpdateProfile {

    private final String loginId;
    private final String name;
    private final String nickname;

    @Builder
    public UpdateProfile(String loginId, String name, String nickname) {
        this.loginId = loginId;
        this.name = name;
        this.nickname = nickname;
    }
}
