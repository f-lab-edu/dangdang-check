package com.dangdang.check.domain.store;

import com.dangdang.check.domain.store.request.GetStoresByCriteria;
import com.dangdang.check.domain.store.request.RegisterStore;
import com.dangdang.check.domain.store.response.StoreInfo;
import com.dangdang.check.domain.store.response.StoreSummaryInfo;
import org.springframework.data.domain.Page;


public interface StoreService {

    StoreInfo registerStore(RegisterStore command);

    StoreInfo getStoreById(Long storeId);

    Page<StoreSummaryInfo> getStoresByCriteria(GetStoresByCriteria criteria);

    StoreInfo approveStore(Long storeId);
}
