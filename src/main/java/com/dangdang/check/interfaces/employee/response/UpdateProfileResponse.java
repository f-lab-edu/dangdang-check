package com.dangdang.check.interfaces.employee.response;

import com.dangdang.check.domain.employee.Role;
import com.dangdang.check.domain.employee.response.EmployeeInfo;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpdateProfileResponse {
    private final Long id;
    private final String name;
    private final String nickname;
    private final String email;
    private final String loginId;
    private final Role role;

    public UpdateProfileResponse(EmployeeInfo employeeInfo) {
        this.id = employeeInfo.getId();
        this.name = employeeInfo.getName();
        this.nickname = employeeInfo.getNickname();
        this.email = employeeInfo.getEmail();
        this.loginId = employeeInfo.getLoginId();
        this.role = employeeInfo.getRole();
    }
}
