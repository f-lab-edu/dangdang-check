package com.dangdang.check.domain.grooming;

import com.dangdang.check.domain.BaseEntity;
import com.dangdang.check.domain.pet.PetEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.InvalidParameterException;

@Getter
@Entity
@Table(name = "grooming_reservation_pets")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroomingReservationPetEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "grooming_reservation_id", nullable = false)
    private GroomingReservationEntity groomingReservation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pet_id", nullable = false)
    private PetEntity pet;

    public GroomingReservationPetEntity(GroomingReservationEntity groomingReservation, PetEntity pet) {
        if(isNotValidParams(groomingReservation, pet)){
            throw new InvalidParameterException();
        }
        this.groomingReservation = groomingReservation;
        this.pet = pet;
    }

    public void modifyGroomingReservation(GroomingReservationEntity reservation) {
        this.groomingReservation = reservation;
    }

    private boolean isNotValidParams(GroomingReservationEntity groomingReservation, PetEntity pet) {
        return groomingReservation == null || pet == null;
    }



}
