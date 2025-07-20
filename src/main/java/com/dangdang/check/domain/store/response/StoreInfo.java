package com.dangdang.check.domain.store.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class StoreInfo {

    private final String storeName;
    private final AddressInfo storeAddress;
    private final String storeEmail;
    private final String mainPhone;
    private final String businessRegistrationNumber;
    private final String businessName;
    private final String representativeName;
    private final String businessType;
    private final AddressInfo businessAddress;
    private final String registrationStatus;
    private final String rejectedReason;

    @Builder
    public StoreInfo(String storeName, AddressInfo storeAddress, String storeEmail, String mainPhone, String businessRegistrationNumber, String businessName, String representativeName, String businessType, AddressInfo businessAddress, String registrationStatus, String rejectedReason) {
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.storeEmail = storeEmail;
        this.mainPhone = mainPhone;
        this.businessRegistrationNumber = businessRegistrationNumber;
        this.businessName = businessName;
        this.representativeName = representativeName;
        this.businessType = businessType;
        this.businessAddress = businessAddress;
        this.registrationStatus = registrationStatus;
        this.rejectedReason = rejectedReason;
    }

    @Getter
    @ToString
    public static class AddressInfo {
        private final String zipCode;
        private final String state;
        private final String city;
        private final String street;
        private final String detail;

        @Builder
        public AddressInfo(String zipCode, String state, String city, String street, String detail) {
            this.zipCode = zipCode;
            this.state = state;
            this.city = city;
            this.street = street;
            this.detail = detail;
        }
    }
}
