package com.dangdang.check.domain.grooming;

import com.dangdang.check.domain.pet.PetEntity;

public class GroomingReservationPetEntityFactory {

    public static GroomingReservationPetEntity from(GroomingReservationEntity groomingReservation, PetEntity pet) {
        return new GroomingReservationPetEntity(groomingReservation, pet);
    }
}
