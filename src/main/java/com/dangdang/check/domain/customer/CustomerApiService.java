package com.dangdang.check.domain.customer;

import com.dangdang.check.domain.customer.request.RegisterCustomerWithPets;
import com.dangdang.check.domain.customer.response.CustomerInfo;

public interface CustomerApiService {

    CustomerInfo registerCustomerWithPets(RegisterCustomerWithPets command);

}
