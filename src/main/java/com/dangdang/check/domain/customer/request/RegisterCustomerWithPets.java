package com.dangdang.check.domain.customer.request;

import com.dangdang.check.domain.customer.PhoneType;
import com.dangdang.check.domain.pet.Gender;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class RegisterCustomerWithPets {

    private final String loginId;
    private final String customerName;
    private final String phoneNumber;
    private final String phoneLabel;
    private final PhoneType phoneType;
    private final String specialNotes;
    private final List<RegisterPet> pets;

    @Builder
    public RegisterCustomerWithPets(String loginId, String customerName, String phoneNumber, String phoneLabel, PhoneType phoneType, String specialNotes, List<RegisterPet> pets) {
        this.loginId = loginId;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.phoneLabel = phoneLabel;
        this.phoneType = phoneType;
        this.specialNotes = specialNotes;
        this.pets = pets;
    }

    @Getter
    public static class RegisterPet {
        private final String name;
        private final Gender gender;
        private final LocalDate birthday;
        private final Boolean neutered;
        private final Boolean vaccinated;
        private final String specialNotes;
        private final Double weight;
        private final String breedName;
        private final String species;

        @Builder
        public RegisterPet(String name, Gender gender, LocalDate birthday, Boolean neutered, Boolean vaccinated, String specialNotes, Double weight, String breedName, String species) {
            this.name = name;
            this.gender = gender;
            this.birthday = birthday;
            this.neutered = neutered;
            this.vaccinated = vaccinated;
            this.specialNotes = specialNotes;
            this.weight = weight;
            this.breedName = breedName;
            this.species = species;
        }
    }
}
