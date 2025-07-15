package com.dangdang.check.domain.store;

import com.dangdang.check.core.employee.EmployeeFindService;
import com.dangdang.check.core.store.StoreCommandService;
import com.dangdang.check.domain.employee.EmployeeEntity;
import com.dangdang.check.domain.store.request.RegisterStore;
import com.dangdang.check.domain.store.response.StoreInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final EmployeeFindService employeeFindService;
    private final StoreCommandService storeCommandService;

    @Override
    @Transactional
    public StoreInfo registerStore(RegisterStore command) {
        EmployeeEntity employee = employeeFindService.findByLoginId(command.getLoginId());
        StoreEntity store = storeCommandService.save(StoreEntityFactory.from(command, BusinessInfoEntityFactory.from(command)));
        employee.addStore(store);
        return StoreEntityFactory.to(store);
    }
}