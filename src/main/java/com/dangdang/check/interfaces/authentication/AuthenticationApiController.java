package com.dangdang.check.interfaces.authentication;

import com.dangdang.check.common.response.CommonResponse;
import com.dangdang.check.domain.verification.AuthenticationApiService;
import com.dangdang.check.domain.verification.request.VerifyEmailCode;
import com.dangdang.check.interfaces.authentication.request.SendVerificationCodeRequest;
import com.dangdang.check.interfaces.authentication.request.VerifyEmailCodeRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class AuthenticationApiController {

    private final AuthenticationApiService authenticationApiService;

    @PostMapping("/email-verification")
    public CommonResponse<Boolean> sendVerificationCode(@RequestBody @Valid SendVerificationCodeRequest request) {
        boolean response = authenticationApiService.sendVerificationCode(request.getEmail());
        return CommonResponse.success(response);
    }

    @PostMapping("/email-verification/confirm")
    public CommonResponse<Boolean> verifyCode(@RequestBody @Valid VerifyEmailCodeRequest request) {
        VerifyEmailCode command = request.toCommand();
        boolean response = authenticationApiService.verifyEmailCode(command);
        return CommonResponse.success(response);
    }
}
