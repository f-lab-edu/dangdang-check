package com.dangdang.check.interfaces.authentication.request;

import com.dangdang.check.domain.verification.request.VerifyPhoneCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class VerifyPhoneCodeRequest {

    @NotBlank(message = "휴대폰 번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^\\d{10,11}$", message = "휴대폰 번호는 10~11자리 숫자로 입력해주세요.")
    private String mobilePhone;

    @NotBlank(message = "code는 필수값입니다")
    @Pattern(regexp = "^[0-9]{6}$", message = "code는 6자리 숫자여야 합니다")
    private String code;

    public VerifyPhoneCode toCommand() {
        return VerifyPhoneCode.builder()
                .mobilePhone(mobilePhone)
                .code(code)
                .build();
    }
}
