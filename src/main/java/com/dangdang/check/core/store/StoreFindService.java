package com.dangdang.check.core.store;

import com.dangdang.check.domain.store.QBusinessInfoEntity;
import com.dangdang.check.domain.store.QStoreEntity;
import com.dangdang.check.domain.store.StoreEntity;
import com.dangdang.check.domain.store.request.GetStoresByCriteria;
import com.dangdang.check.domain.store.response.QStoreSummaryInfo;
import com.dangdang.check.domain.store.response.StoreSummaryInfo;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreFindService {

    private final JPAQueryFactory jpaQueryFactory;
    private final StoreJpaRepository storeJpaRepository;

    @Transactional(readOnly = true)
    public StoreEntity findById(Long id) {
        return storeJpaRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public Page<StoreSummaryInfo> findByCriteria(GetStoresByCriteria criteria) {
        QStoreEntity store = QStoreEntity.storeEntity;
        QBusinessInfoEntity businessInfo = QBusinessInfoEntity.businessInfoEntity;

        BooleanBuilder predicate = new BooleanBuilder();

        if (criteria.getRegistrationStatus() != null) {
            predicate.and(businessInfo.registrationStatus.eq(criteria.getRegistrationStatus()));
        }

        List<StoreSummaryInfo> content = jpaQueryFactory.select(new QStoreSummaryInfo(
                        store.id,
                        store.name,
                        businessInfo.representativeName,
                        businessInfo.registrationStatus,
                        store.mainPhone)
                ).from(store)
                .join(store.businessInfo, businessInfo)
                .where(predicate)
                .offset(criteria.getPageable().getOffset())
                .limit(criteria.getPageable().getPageSize())
                .fetch();

        Long total = jpaQueryFactory
                .select(store.count())
                .from(store)
                .join(store.businessInfo, businessInfo)
                .where(predicate)
                .fetchOne();

        return new PageImpl<>(content, criteria.getPageable(), total != null ? total : 0L);
    }
}
