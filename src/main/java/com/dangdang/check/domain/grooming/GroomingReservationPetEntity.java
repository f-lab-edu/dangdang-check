package com.dangdang.check.domain.grooming;

import com.dangdang.check.domain.BaseEntity;
import com.dangdang.check.domain.pet.PetEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "grooming_reservation_pets")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroomingReservationPetEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grooming_reservation_id", nullable = false)
    private GroomingReservationEntity groomingReservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private PetEntity pet;
}
