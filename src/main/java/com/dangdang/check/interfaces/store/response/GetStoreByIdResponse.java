package com.dangdang.check.interfaces.store.response;

import com.dangdang.check.domain.store.response.StoreInfo;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GetStoreByIdResponse {
    private final String storeName;
    private final RegisterStoreResponse.AddressResponse storeAddress;
    private final String storeEmail;
    private final String mainPhone;
    private final String businessRegistrationNumber;
    private final String businessName;
    private final String representativeName;
    private final String businessType;
    private final RegisterStoreResponse.AddressResponse businessAddress;
    private final String registrationStatus;
    private final String rejectedReason;

    public GetStoreByIdResponse(StoreInfo storeInfo) {
        this.storeName = storeInfo.getStoreName();
        this.storeAddress = new RegisterStoreResponse.AddressResponse(storeInfo.getStoreAddress());
        this.storeEmail = storeInfo.getStoreEmail();
        this.mainPhone = storeInfo.getMainPhone();
        this.businessRegistrationNumber = storeInfo.getBusinessRegistrationNumber();
        this.businessName = storeInfo.getBusinessName();
        this.representativeName = storeInfo.getRepresentativeName();
        this.businessType = storeInfo.getBusinessType();
        this.businessAddress = new RegisterStoreResponse.AddressResponse(storeInfo.getBusinessAddress());
        this.registrationStatus = storeInfo.getRegistrationStatus();
        this.rejectedReason = storeInfo.getRejectedReason();
    }
}
