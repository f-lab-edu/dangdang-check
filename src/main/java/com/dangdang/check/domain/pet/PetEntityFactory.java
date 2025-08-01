package com.dangdang.check.domain.pet;

import com.dangdang.check.domain.customer.request.RegisterCustomerWithPets.RegisterPet;

public class PetEntityFactory {

    public static PetEntity from(RegisterPet command) {
        return PetEntity.builder()
                .name(command.getName())
                .gender(command.getGender())
                .neutered(command.getNeutered())
                .vaccinated(command.getVaccinated())
                .specialNotes(command.getSpecialNotes())
                .birthday(command.getBirthday())
                .weight(command.getWeight())
                .species(command.getSpecies())
                .breedName(command.getBreedName())
                .build();
    }
}
