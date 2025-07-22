package com.dangdang.check.domain.store;

import com.dangdang.check.domain.BaseEntity;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "stores")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String mainPhone;
    @Embedded
    private Address address;
    private boolean isDeleted = false;
    private LocalDateTime deletedAt;

    @JoinColumn(name = "business_info_id")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private BusinessInfoEntity businessInfo;

    @Builder
    public StoreEntity(String name, String email, String mainPhone, Address address, BusinessInfoEntity businessInfo) {
        if (isNotValidParams(name, email, mainPhone, address, businessInfo)) {
            throw new InvalidParameterException();
        }

        this.name = name;
        this.email = email;
        this.mainPhone = mainPhone;
        this.address = address;
        this.businessInfo = businessInfo;
    }

    public void approve() {
        if (businessInfo != null) {
            businessInfo.approve();
        } else {
            throw new EntityNotFoundException();
        }
    }

    public void reject(String rejectedReason) {
        if (businessInfo != null) {
            businessInfo.reject(rejectedReason);
        } else {
            throw new EntityNotFoundException();
        }
    }

    private boolean isNotValidParams(String name, String email, String mainPhone, Address address, BusinessInfoEntity businessInfo) {
        return StringUtils.isBlank(name)
                || StringUtils.isBlank(email)
                || StringUtils.isBlank(mainPhone)
                || address == null
                || businessInfo == null;
    }
}