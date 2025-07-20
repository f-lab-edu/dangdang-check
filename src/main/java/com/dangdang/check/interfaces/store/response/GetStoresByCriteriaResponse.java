package com.dangdang.check.interfaces.store.response;

import com.dangdang.check.domain.store.RegistrationStatus;
import com.dangdang.check.domain.store.response.StoreSummaryInfo;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GetStoresByCriteriaResponse {
    private final Long storeId;
    private final String storeName;
    private final String ownerName;
    private final RegistrationStatus registrationStatus;
    private final String mainPhone;

    public GetStoresByCriteriaResponse(StoreSummaryInfo storeSummaryInfo) {
        this.storeId = storeSummaryInfo.getStoreId();
        this.storeName = storeSummaryInfo.getStoreName();
        this.ownerName = storeSummaryInfo.getOwnerName();
        this.registrationStatus = storeSummaryInfo.getRegistrationStatus();
        this.mainPhone = storeSummaryInfo.getMainPhone();
    }

}
