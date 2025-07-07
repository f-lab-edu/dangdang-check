package com.dangdang.check.interfaces.authentication.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SendPhoneVerificationCodeRequest {

    @NotBlank(message = "휴대폰 번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^\\d{10,11}$", message = "휴대폰 번호는 10~11자리 숫자로 입력해주세요.")
    private String mobilePhone;
}
