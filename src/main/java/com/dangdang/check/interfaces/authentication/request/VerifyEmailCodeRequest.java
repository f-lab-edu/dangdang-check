package com.dangdang.check.interfaces.authentication.request;

import com.dangdang.check.domain.verification.request.VerifyEmailCode;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class VerifyEmailCodeRequest {

    @NotBlank(message = "email은 필수값입니다")
    @Email(message = "email 형식에 맞아야 합니다")
    private String email;

    @NotBlank(message = "code는 필수값입니다")
    @Pattern(regexp = "^[0-9]{6}$", message = "code는 6자리 숫자여야 합니다")
    private String code;

    public VerifyEmailCode toCommand() {
        return VerifyEmailCode.builder()
                .email(email)
                .code(code)
                .build();
    }
}
