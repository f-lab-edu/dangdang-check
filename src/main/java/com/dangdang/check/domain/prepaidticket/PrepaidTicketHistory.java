package com.dangdang.check.domain.prepaidticket;

import com.dangdang.check.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "prepaid_ticket_histories")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PrepaidTicketHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal changeAmount;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prepaid_ticket_id", nullable = false)
    private PrepaidTicket prepaidTicket;
}
