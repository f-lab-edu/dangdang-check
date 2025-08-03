package com.dangdang.check.domain.customer;

import com.dangdang.check.domain.customer.request.RegisterCustomerWithPets;

public class CustomerPhoneEntityFactory {

    public static CustomerPhoneEntity from(RegisterCustomerWithPets command) {
                 return CustomerPhoneEntity.builder()
                .label(command.getPhoneLabel())
                .phoneNumber(command.getPhoneNumber())
                .phoneType(command.getPhoneType())
                .build();
    }
}
