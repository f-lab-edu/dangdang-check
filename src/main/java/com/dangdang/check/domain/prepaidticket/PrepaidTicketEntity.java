package com.dangdang.check.domain.prepaidticket;

import com.dangdang.check.domain.BaseEntity;
import com.dangdang.check.domain.customer.CustomerEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "prepaid_tickets")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PrepaidTicketEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal balance;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;
}
