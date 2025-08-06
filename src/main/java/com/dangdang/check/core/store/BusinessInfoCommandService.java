package com.dangdang.check.core.store;

import com.dangdang.check.domain.store.BusinessInfoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BusinessInfoCommandService {

    private final BusinessInfoJpaRepository businessInfoJpaRepository;

    public BusinessInfoEntity save(BusinessInfoEntity businessInfoEntity) {
        return businessInfoJpaRepository.save(businessInfoEntity);
    }
}
