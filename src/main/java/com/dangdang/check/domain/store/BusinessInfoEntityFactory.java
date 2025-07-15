package com.dangdang.check.domain.store;

import com.dangdang.check.domain.store.request.RegisterStore;

public class BusinessInfoEntityFactory {

    public static BusinessInfoEntity from(RegisterStore command) {
        return BusinessInfoEntity.builder()
                .businessName(command.getBusinessName())
                .businessRegistrationNumber(command.getBusinessRegistrationNumber())
                .businessType(command.getBusinessType())
                .representativeName(command.getRepresentativeName())
                .address(new Address(
                        command.getBusinessAddress().getZipCode(),
                        command.getBusinessAddress().getState(),
                        command.getBusinessAddress().getCity(),
                        command.getBusinessAddress().getStreet(),
                        command.getBusinessAddress().getDetail()
                ))
                .build();
    }
}
