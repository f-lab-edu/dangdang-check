package com.dangdang.check.domain.employee;

import com.dangdang.check.domain.employee.request.RegisterEmployee;
import com.dangdang.check.domain.employee.request.UpdatePassword;
import com.dangdang.check.domain.employee.request.UpdateProfile;
import com.dangdang.check.domain.employee.response.EmployeeInfo;

public interface EmployeeApiService {

    EmployeeInfo registerEmployee(RegisterEmployee command);

    EmployeeInfo updateProfile(UpdateProfile command);

    EmployeeInfo updatePassword(UpdatePassword command);
}
