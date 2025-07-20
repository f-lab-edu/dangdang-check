package com.dangdang.check.interfaces.store.response;


import com.dangdang.check.domain.store.response.StoreInfo;
import com.dangdang.check.domain.store.response.StoreInfo.AddressInfo;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RegisterStoreResponse {

    private final String storeName;
    private final AddressResponse storeAddress;
    private final String storeEmail;
    private final String mainPhone;
    private final String businessRegistrationNumber;
    private final String businessName;
    private final String representativeName;
    private final String businessType;
    private final AddressResponse businessAddress;
    private final String registrationStatus;
    private final String rejectedReason;


    public RegisterStoreResponse(StoreInfo storeInfo) {
        this.storeName = storeInfo.getStoreName();
        this.storeAddress = new AddressResponse(storeInfo.getStoreAddress());
        this.storeEmail = storeInfo.getStoreEmail();
        this.mainPhone = storeInfo.getMainPhone();
        this.businessRegistrationNumber = storeInfo.getBusinessRegistrationNumber();
        this.businessName = storeInfo.getBusinessName();
        this.representativeName = storeInfo.getRepresentativeName();
        this.businessType = storeInfo.getBusinessType();
        this.businessAddress = new AddressResponse(storeInfo.getBusinessAddress());
        this.registrationStatus = storeInfo.getRegistrationStatus();
        this.rejectedReason = storeInfo.getRejectedReason();
    }

    @Getter
    @ToString
    public static class AddressResponse {
        private final String zipCode;
        private final String state;
        private final String city;
        private final String street;
        private final String detail;

        public AddressResponse(AddressInfo addressInfo) {
            this.zipCode = addressInfo.getZipCode();
            this.state = addressInfo.getState();
            this.city = addressInfo.getCity();
            this.street = addressInfo.getStreet();
            this.detail = addressInfo.getDetail();
        }
    }
}
