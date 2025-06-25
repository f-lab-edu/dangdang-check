package com.dangdang.check.interfaces.employee;


import com.dangdang.check.common.response.CommonResponse;
import com.dangdang.check.domain.employee.response.EmployeeInfo;
import com.dangdang.check.domain.employee.EmployeeService;
import com.dangdang.check.domain.employee.request.RegisterEmployee;
import com.dangdang.check.interfaces.employee.request.RegisterEmployeeRequest;
import com.dangdang.check.interfaces.employee.response.RegisterEmployeeResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmployeeApiController {

    private final EmployeeService employeeService;

    @PostMapping("/api/employees")
    public CommonResponse<RegisterEmployeeResponse> registerEmployee(@RequestBody @Valid RegisterEmployeeRequest request) {
        RegisterEmployee command = request.toCommand();
        EmployeeInfo employeeInfo = employeeService.registerEmployee(command);
        RegisterEmployeeResponse response = new RegisterEmployeeResponse(employeeInfo);
        return CommonResponse.success(response);
    }
}
