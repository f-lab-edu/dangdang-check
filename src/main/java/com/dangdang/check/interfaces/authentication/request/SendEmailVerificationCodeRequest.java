package com.dangdang.check.interfaces.authentication.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SendEmailVerificationCodeRequest {

    @NotBlank(message = "email은 필수값입니다")
    @Email(message = "email 형식에 맞아야 합니다")
    private String email;
}
