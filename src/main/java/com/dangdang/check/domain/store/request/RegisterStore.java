package com.dangdang.check.domain.store.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RegisterStore {
    private final String loginId;
    private final String storeName;
    private final String email;
    private final String mainPhone;
    private final Address storeAddress;
    private final String businessName;
    private final String businessRegistrationNumber;
    private final String businessType;
    private final String representativeName;
    private final Address businessAddress;

    @Builder
    public RegisterStore(String loginId, String storeName, String email, String mainPhone, Address storeAddress, String businessName, String businessRegistrationNumber, String businessType, String representativeName, Address businessAddress) {
        this.loginId = loginId;
        this.storeName = storeName;
        this.email = email;
        this.mainPhone = mainPhone;
        this.storeAddress = storeAddress;
        this.businessName = businessName;
        this.businessRegistrationNumber = businessRegistrationNumber;
        this.businessType = businessType;
        this.representativeName = representativeName;
        this.businessAddress = businessAddress;
    }


    @Getter
    @ToString
    public static class Address {
        private final String zipCode;
        private final String state;
        private final String city;
        private final String street;
        private final String detail;

        @Builder
        public Address(String zipCode, String state, String city, String street, String detail) {
            this.zipCode = zipCode;
            this.state = state;
            this.city = city;
            this.street = street;
            this.detail = detail;
        }

    }

}
