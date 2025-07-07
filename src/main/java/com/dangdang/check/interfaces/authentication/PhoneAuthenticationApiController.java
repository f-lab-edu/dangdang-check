package com.dangdang.check.interfaces.authentication;

import com.dangdang.check.common.response.CommonResponse;
import com.dangdang.check.domain.verification.PhoneAuthenticationApiService;
import com.dangdang.check.interfaces.authentication.request.SendPhoneVerificationCodeRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees/phone-verification")
@RequiredArgsConstructor
public class PhoneAuthenticationApiController {

    private final PhoneAuthenticationApiService phoneAuthenticationApiService;

    @PostMapping
    public CommonResponse<Boolean> sendPhoneVerificationCode(@RequestBody @Valid SendPhoneVerificationCodeRequest request) {
        boolean response = phoneAuthenticationApiService.sendVerificationCode(request.getMobilePhone());
        return CommonResponse.success(response);
    }
}
