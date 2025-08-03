package com.dangdang.check.domain.grooming;

import com.dangdang.check.domain.BaseEntity;

import com.dangdang.check.domain.customer.CustomerEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "grooming_reservations")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroomingReservationEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String title;
    @Lob
    private String groomingRequest;
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus = ReservationStatus.RESERVED;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    @OneToMany(mappedBy = "groomingReservation")
    private List<GroomingReservationPetEntity> groomingReservationPets = new ArrayList<>();

    @Builder
    public GroomingReservationEntity(LocalDateTime startAt, LocalDateTime endAt, String title, String groomingRequest, CustomerEntity customer) {
        if(isNotValidParams(startAt, endAt, title, customer)) {
            throw new InvalidParameterException("예약 필수 항목이 누락되었습니다.");
        }

        this.startAt = startAt;
        this.endAt = endAt;
        this.title = title;
        this.groomingRequest = groomingRequest;
        this.customer = customer;
    }

    public void addGroomingReservationPet(GroomingReservationPetEntity groomingReservationPet) {
        if (!groomingReservationPet.getPet().getCustomer().equals(this.customer)) {
            throw new IllegalStateException("펫의 주인이 예약 고객과 다릅니다.");
        }

        groomingReservationPets.add(groomingReservationPet);
        groomingReservationPet.modifyGroomingReservation(this);
    }

    private boolean isNotValidParams(LocalDateTime startAt, LocalDateTime endAt, String title, CustomerEntity customer) {
        return startAt == null || endAt == null || title == null || customer == null;
    }
}
