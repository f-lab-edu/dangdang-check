package com.dangdang.check.interfaces.employee.request;

import com.dangdang.check.domain.employee.request.UpdateProfile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank(message = "loginId는 필수값입니다.")
    @Size(min = 5, max = 15, message = "loginId는 최소 5자, 최대 15자이어야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "loginId는 영문자와 숫자만 허용됩니다.")
    private String loginId;

    public UpdateProfile toCommand() {
        return UpdateProfile.builder()
                .name(name)
                .nickname(nickname)
                .loginId(loginId)
                .build();

    }
}
