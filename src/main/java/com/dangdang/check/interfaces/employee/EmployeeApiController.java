package com.dangdang.check.interfaces.employee;


import com.dangdang.check.common.argumentresolver.Login;
import com.dangdang.check.common.response.CommonResponse;
import com.dangdang.check.domain.employee.EmployeeApiService;
import com.dangdang.check.domain.employee.request.UpdatePassword;
import com.dangdang.check.domain.employee.request.UpdateProfile;
import com.dangdang.check.domain.employee.response.EmployeeInfo;
import com.dangdang.check.domain.employee.request.RegisterEmployee;
import com.dangdang.check.interfaces.employee.request.RegisterEmployeeRequest;
import com.dangdang.check.interfaces.employee.request.UpdatePasswordRequest;
import com.dangdang.check.interfaces.employee.request.UpdateProfileRequest;
import com.dangdang.check.interfaces.employee.response.RegisterEmployeeResponse;
import com.dangdang.check.interfaces.employee.response.UpdatePasswordResponse;
import com.dangdang.check.interfaces.employee.response.UpdateProfileResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeApiController {

    private final EmployeeApiService employeeApiService;

    @PostMapping
    public CommonResponse<RegisterEmployeeResponse> registerEmployee(@RequestBody @Valid RegisterEmployeeRequest request) {
        RegisterEmployee command = request.toCommand();
        EmployeeInfo employeeInfo = employeeApiService.registerEmployee(command);
        RegisterEmployeeResponse response = new RegisterEmployeeResponse(employeeInfo);
        return CommonResponse.success(response);
    }

    @PatchMapping("/me/profile")
    public CommonResponse<UpdateProfileResponse> updateProfile(@RequestBody @Valid UpdateProfileRequest request,
                                                               @Login String loginId) {
        UpdateProfile command = request.toCommand(loginId);
        EmployeeInfo employeeInfo = employeeApiService.updateProfile(command);
        UpdateProfileResponse response = new UpdateProfileResponse(employeeInfo);
        return CommonResponse.success(response);
    }

    @PatchMapping("/me/password")
    public CommonResponse<UpdatePasswordResponse> updatePassword(@RequestBody @Valid UpdatePasswordRequest request,
                                                                 @Login String loginId) {
        UpdatePassword command = request.toCommand(loginId);
        EmployeeInfo employeeInfo = employeeApiService.updatePassword(command);
        UpdatePasswordResponse response = new UpdatePasswordResponse(employeeInfo);
        return CommonResponse.success(response);
    }
}
