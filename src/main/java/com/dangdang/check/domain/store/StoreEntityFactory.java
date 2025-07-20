package com.dangdang.check.domain.store;

import com.dangdang.check.domain.store.request.RegisterStore;
import com.dangdang.check.domain.store.response.StoreInfo;

public class StoreEntityFactory {

    public static StoreEntity from(RegisterStore command, BusinessInfoEntity businessInfo) {
        return StoreEntity.builder()
                .name(command.getBusinessName())
                .email(command.getEmail())
                .mainPhone(command.getMainPhone())
                .address(new Address(
                        command.getStoreAddress().getZipCode(),
                        command.getStoreAddress().getState(),
                        command.getStoreAddress().getCity(),
                        command.getStoreAddress().getStreet(),
                        command.getStoreAddress().getDetail()
                ))
                .businessInfo(businessInfo)
                .build();
    }

    public static StoreInfo to(StoreEntity store) {
        return StoreInfo.builder()
                .storeName(store.getName())
                .storeAddress(StoreInfo.AddressInfo.builder()
                        .zipCode(store.getAddress().getZipCode())
                        .city(store.getAddress().getCity())
                        .state(store.getAddress().getState())
                        .street(store.getAddress().getStreet())
                        .detail(store.getAddress().getDetail())
                        .build())
                .storeEmail(store.getEmail())
                .mainPhone(store.getMainPhone())
                .businessRegistrationNumber(store.getBusinessInfo().getBusinessRegistrationNumber())
                .businessName(store.getBusinessInfo().getBusinessName())
                .representativeName(store.getBusinessInfo().getRepresentativeName())
                .businessType(store.getBusinessInfo().getBusinessType())
                .businessAddress(StoreInfo.AddressInfo.builder()
                        .zipCode(store.getBusinessInfo().getAddress().getZipCode())
                        .city(store.getBusinessInfo().getAddress().getCity())
                        .state(store.getBusinessInfo().getAddress().getState())
                        .street(store.getBusinessInfo().getAddress().getStreet())
                        .detail(store.getBusinessInfo().getAddress().getDetail())
                        .build())
                .registrationStatus(store.getBusinessInfo().getRegistrationStatus().toString())
                .rejectedReason(store.getBusinessInfo().getRejectedReason())
                .build();
    }
}
