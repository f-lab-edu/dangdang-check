package com.dangdang.check.interfaces.employee.request;

import com.dangdang.check.domain.employee.request.UpdateProfile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpdateProfileRequest {

    @NotBlank(message = "name은 필수값입니다")
    @Size(min = 1, max = 10, message = "name은 최소 1글자, 최대 10글자입니다.")
    private String name;

    @NotBlank(message = "nickname은 필수값입니다")
    @Size(min = 2, max = 8, message = "nickname은 최소 2글자, 최대 8글자 입니다.")
    private String nickname;

    public UpdateProfile toCommand(String loginId) {
        return UpdateProfile.builder()
                .name(name)
                .nickname(nickname)
                .loginId(loginId)
                .build();

    }
}
