package com.dangdang.check.interfaces.employee.request;

import com.dangdang.check.domain.employee.request.UpdatePassword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpdatePasswordRequest {

    @NotBlank(message = "newPassword는 필수값입니다")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$",
            message = "비밀번호는 '숫자', '문자', '특수문자'를 최소 1개 이상 포함하며, 8자에서 16자까지 허용됩니다.")
    private String currentPassword;

    @NotBlank(message = "newPassword는 필수값입니다")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$",
            message = "비밀번호는 '숫자', '문자', '특수문자'를 최소 1개 이상 포함하며, 8자에서 16자까지 허용됩니다.")
    private String newPassword;

    public UpdatePassword toCommand(String loginId) {
        return UpdatePassword.builder()
                .loginId(loginId)
                .currentPassword(currentPassword)
                .newPassword(newPassword)
                .build();

    }

}
