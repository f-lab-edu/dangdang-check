package com.dangdang.check.domain.customer;

import com.dangdang.check.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "customer_phones")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomerPhoneEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label; // 전화번호 별칭(예: "엄마", "아빠")
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private PhoneType phoneType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;
    private boolean isDeleted = false;
    private LocalDateTime deletedAt;


    @Builder
    public CustomerPhoneEntity(String label, String phoneNumber, PhoneType phoneType) {
        if(isNotValidParams(label, phoneNumber, phoneType)){
            throw new InvalidParameterException();
        }

        this.label = label;
        this.phoneNumber = phoneNumber;
        this.phoneType = phoneType;
    }

    public void modifyCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    private boolean isNotValidParams(String label, String phoneNumber, PhoneType phoneType) {
        return !StringUtils.hasText(label) ||
                !StringUtils.hasText(phoneNumber) ||
                phoneType == null;
    }
}