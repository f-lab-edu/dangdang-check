package com.dangdang.check.domain.store.request;

import com.dangdang.check.domain.store.RegistrationStatus;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Pageable;

@Getter
@ToString
public class GetStoresByCriteria {
    private final Pageable pageable;
    private final RegistrationStatus registrationStatus;

    public GetStoresByCriteria(Pageable pageable, RegistrationStatus registrationStatus) {
        this.pageable = pageable;
        this.registrationStatus = registrationStatus;
    }
}
