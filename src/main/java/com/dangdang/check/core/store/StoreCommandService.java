package com.dangdang.check.core.store;

import com.dangdang.check.domain.store.StoreEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreCommandService {

    private final StoreJpaRepository storeJpaRepository;

    public StoreEntity save(StoreEntity store) {
        return storeJpaRepository.save(store);
    }

}
