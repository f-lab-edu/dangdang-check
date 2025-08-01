package com.dangdang.check.interfaces.customer;

import com.dangdang.check.common.argumentresolver.Login;
import com.dangdang.check.common.response.CommonResponse;
import com.dangdang.check.domain.customer.CustomerApiService;
import com.dangdang.check.domain.customer.request.RegisterCustomerWithPets;
import com.dangdang.check.domain.customer.response.CustomerInfo;
import com.dangdang.check.interfaces.customer.request.RegisterCustomerWithPetsRequest;
import com.dangdang.check.interfaces.customer.response.RegisterCustomerWithPetsResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerApiController {

    private final CustomerApiService customerApiService;

    @PostMapping("/api/customers")
    public CommonResponse<RegisterCustomerWithPetsResponse> registerCustomerWithPets(@Login String loginId,
                                                                                     @RequestBody @Valid RegisterCustomerWithPetsRequest request) {
        RegisterCustomerWithPets command = request.toCommand(loginId);
        CustomerInfo customerInfo = customerApiService.registerCustomerWithPets(command);
        RegisterCustomerWithPetsResponse response = new RegisterCustomerWithPetsResponse(customerInfo);
        return CommonResponse.success(response);
    }
}