package com.dangdang.check.core.store;

import com.dangdang.check.domain.store.StoreEntity;
import com.dangdang.check.domain.store.StoreRepositoryCustom;
import com.dangdang.check.domain.store.request.GetStoresByCriteria;
import com.dangdang.check.domain.store.response.StoreSummaryInfo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class StoreFindService {

    private final StoreRepositoryCustom storeRepositoryCustom;
    private final StoreJpaRepository storeJpaRepository;

    @Transactional(readOnly = true)
    public StoreEntity findById(Long id) {
        return storeJpaRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public Page<StoreSummaryInfo> findByCriteria(GetStoresByCriteria criteria) {
        return storeRepositoryCustom.findByCriteria(criteria);
    }
}
