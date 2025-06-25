package com.dangdang.check.domain.employee.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpdateProfile {

    private final Long employeeId;
    private final String name;
    private final String nickname;

    @Builder
    public UpdateProfile(Long employeeId, String name, String nickname) {
        this.employeeId = employeeId;
        this.name = name;
        this.nickname = nickname;
    }
}
