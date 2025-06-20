package com.dangdang.check.domain.store;

import com.dangdang.check.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "business_infos")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BusinessInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String businessName;
    private String businessRegistrationNumber;
    private String businessType;
    private String representativeName;
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private RegistrationStatus registrationStatus;
    private String rejectedReason;
}