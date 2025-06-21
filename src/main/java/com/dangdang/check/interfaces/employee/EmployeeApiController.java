package com.dangdang.check.interfaces.employee;


import com.dangdang.check.common.response.CommonResponse;
import com.dangdang.check.domain.employee.EmployeeCommand;
import com.dangdang.check.domain.employee.EmployeeInfo;
import com.dangdang.check.domain.employee.EmployeeService;
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
    public CommonResponse<EmployeeDto.RegisterEmployeeResponse> registerEmployee(@RequestBody @Valid EmployeeDto.RegisterEmployeeRequest request) {
        EmployeeCommand.RegisterEmployeeRequest command = request.toCommand();
        EmployeeInfo employeeInfo = employeeService.registerEmployee(command);
        EmployeeDto.RegisterEmployeeResponse response = new EmployeeDto.RegisterEmployeeResponse(employeeInfo);
        return CommonResponse.success(response);
    }
}
