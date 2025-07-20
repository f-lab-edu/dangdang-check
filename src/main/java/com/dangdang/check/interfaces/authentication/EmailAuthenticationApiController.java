package com.dangdang.check.interfaces.authentication;

import com.dangdang.check.common.response.CommonResponse;
import com.dangdang.check.domain.verification.EmailAuthenticationApiService;
import com.dangdang.check.domain.verification.request.VerifyEmailCode;
import com.dangdang.check.interfaces.authentication.request.SendEmailVerificationCodeRequest;
import com.dangdang.check.interfaces.authentication.request.VerifyEmailCodeRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees/email-verification")
@RequiredArgsConstructor
public class EmailAuthenticationApiController {

    private final EmailAuthenticationApiService emailauthenticationApiService;

    @PostMapping
    public void sendEmailVerificationCode(@RequestBody @Valid SendEmailVerificationCodeRequest request) {
        emailauthenticationApiService.sendVerificationCode(request.getEmail());
    }

    @PostMapping("/confirm")
    public CommonResponse<Boolean> verifyEmailCode(@RequestBody @Valid VerifyEmailCodeRequest request) {
        VerifyEmailCode command = request.toCommand();
        boolean response = emailauthenticationApiService.verifyCode(command);
        return CommonResponse.success(response);
    }
}
