package com.dangdang.check.domain.store.response;

import com.dangdang.check.domain.store.RegistrationStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class StoreSummaryInfo {
    private final Long storeId;
    private final String storeName;
    private final String ownerName;
    private final RegistrationStatus registrationStatus;
    private final String mainPhone;


    @QueryProjection
    public StoreSummaryInfo(Long storeId, String storeName, String ownerName, RegistrationStatus registrationStatus, String mainPhone) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.ownerName = ownerName;
        this.registrationStatus = registrationStatus;
        this.mainPhone = mainPhone;
    }
}
