package com.dangdang.check.domain.store;

import com.dangdang.check.domain.store.request.GetStoresByCriteria;
import com.dangdang.check.domain.store.response.StoreSummaryInfo;
import org.springframework.data.domain.Page;

public interface StoreRepositoryCustom {
    Page<StoreSummaryInfo> findByCriteria(GetStoresByCriteria criteria);
}
