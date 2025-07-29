package com.dangdang.check.core.store;

import com.dangdang.check.domain.store.BusinessInfoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BusinessInfoCommandService {

    private final BusinessInfoJpaRepository businessInfoJpaRepository;

    @Transactional
    public BusinessInfoEntity save(BusinessInfoEntity businessInfoEntity) {
        return businessInfoJpaRepository.save(businessInfoEntity);
    }
}
