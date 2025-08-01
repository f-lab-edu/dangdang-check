package com.dangdang.check.domain.customer;

import com.dangdang.check.domain.customer.request.RegisterCustomerWithPets;

public class CustomerEntityFactory {

    public static CustomerEntity from(RegisterCustomerWithPets command) {
        return CustomerEntity.builder()
                .name(command.getCustomerName())
                .specialNotes(command.getSpecialNotes())
                .build();
    }
}
