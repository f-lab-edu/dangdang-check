package com.dangdang.check.interfaces.store.request;

import com.dangdang.check.domain.store.RegistrationStatus;
import com.dangdang.check.domain.store.request.GetStoresByCriteria;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Pageable;

@Getter
@ToString
public class GetStoresByCriteriaRequest {

    private String registrationStatus;

    public GetStoresByCriteria toCriteria(Pageable pageable) {
        return GetStoresByCriteria.builder()
                .pageable(pageable)
                .registrationStatus(registrationStatus != null ? RegistrationStatus.valueOf(registrationStatus) : null)
                .build();
    }
}
