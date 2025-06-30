package com.dangdang.check.domain.customer;

import com.dangdang.check.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
}