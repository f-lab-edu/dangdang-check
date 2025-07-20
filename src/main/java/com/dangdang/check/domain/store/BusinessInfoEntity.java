package com.dangdang.check.domain.store;

import com.dangdang.check.domain.BaseEntity;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.InvalidParameterException;

@Getter
@Entity
@Table(name = "business_infos")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BusinessInfoEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String businessName;
    @Column(unique = true)
    private String businessRegistrationNumber;
    private String businessType;
    private String representativeName;
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private RegistrationStatus registrationStatus = RegistrationStatus.PENDING;
    private String rejectedReason;

    @Builder
    public BusinessInfoEntity(String businessName, String businessRegistrationNumber, String businessType, String representativeName, Address address) {
        if (isNotValidParams(businessName, businessRegistrationNumber, businessType, representativeName, address)) {
            throw new InvalidParameterException();
        }

        this.businessName = businessName;
        this.businessRegistrationNumber = businessRegistrationNumber;
        this.businessType = businessType;
        this.representativeName = representativeName;
        this.address = address;
    }

    public void approve() {
        this.registrationStatus = RegistrationStatus.APPROVED;
    }

    public void reject(String rejectedReason) {
        this.registrationStatus = RegistrationStatus.REJECTED;
        this.rejectedReason = rejectedReason;
    }

    private boolean isNotValidParams(String businessName, String businessRegistrationNumber, String businessType, String representativeName, Address address) {
        return StringUtils.isBlank(businessName)
                || StringUtils.isBlank(businessRegistrationNumber)
                || StringUtils.isBlank(businessType)
                || StringUtils.isBlank(representativeName)
                || address == null;
    }
}