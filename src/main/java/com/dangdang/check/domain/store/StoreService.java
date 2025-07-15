package com.dangdang.check.domain.store;

import com.dangdang.check.domain.store.request.RegisterStore;
import com.dangdang.check.domain.store.response.StoreInfo;

public interface StoreService {

    StoreInfo registerStore(RegisterStore command);
}
