package com.dangdang.check.domain.store;

import com.dangdang.check.core.employee.EmployeeFindService;
import com.dangdang.check.core.store.BusinessInfoCommandService;
import com.dangdang.check.core.store.StoreCommandService;
import com.dangdang.check.core.store.StoreFindService;
import com.dangdang.check.domain.employee.EmployeeEntity;
import com.dangdang.check.domain.store.request.GetStoresByCriteria;
import com.dangdang.check.domain.store.request.RegisterStore;
import com.dangdang.check.domain.store.response.StoreInfo;
import com.dangdang.check.domain.store.response.StoreSummaryInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreFindService storeFindService;
    private final EmployeeFindService employeeFindService;
    private final StoreCommandService storeCommandService;
    private final BusinessInfoCommandService businessInfoCommandService;

    @Override
    @Transactional
    public StoreInfo registerStore(RegisterStore command) {
        EmployeeEntity employee = employeeFindService.findByLoginId(command.getLoginId());
        BusinessInfoEntity businessInfo = businessInfoCommandService.save(BusinessInfoEntityFactory.from(command));
        StoreEntity store = storeCommandService.save(StoreEntityFactory.from(command, businessInfo));
        employee.addStore(store);
        return StoreEntityFactory.to(store);
    }

    @Override
    @Transactional(readOnly = true)
    public StoreInfo getStoreById(Long storeId) {;
        return StoreEntityFactory.to(storeFindService.findById(storeId));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StoreSummaryInfo> getStoresByCriteria(GetStoresByCriteria criteria) {
        return storeFindService.findByCriteria(criteria);
    }

    @Override
    @Transactional
    public StoreInfo approveStore(Long storeId) {
        StoreEntity store = storeFindService.findById(storeId);
        store.approve();
        return StoreEntityFactory.to(store);
    }

    @Override
    @Transactional
    public StoreInfo rejectStore(Long storeId, String rejectedReason) {
        StoreEntity store = storeFindService.findById(storeId);
        store.reject(rejectedReason);
        return StoreEntityFactory.to(store);
    }
}