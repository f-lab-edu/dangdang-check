package com.dangdang.check.domain.customer.response;

import com.dangdang.check.domain.customer.CustomerEntity;
import com.dangdang.check.domain.customer.CustomerPhoneEntity;
import com.dangdang.check.domain.customer.PhoneType;
import com.dangdang.check.domain.pet.PetEntity;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CustomerInfo {

    private final Long id;
    private final String name;
    private final String specialNotes;
    private final List<CustomerPhoneInfo> phones;
    private final List<PetInfo> pets;

    public CustomerInfo(CustomerEntity customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.specialNotes = customer.getSpecialNotes();
        this.phones = customer.getCustomerPhones() != null
                ? customer.getCustomerPhones().stream()
                .map(CustomerPhoneInfo::new)
                .collect(Collectors.toList())
                : Collections.emptyList();;
        this.pets = customer.getPets() != null
                ? customer.getPets().stream()
                .map(PetInfo::new)
                .collect(Collectors.toList())
                : Collections.emptyList();;
    }

    @Getter
    public static class PetInfo {
        private final Long id;
        private final String name;
        private final Boolean vaccinated;
        private final String species;
        private final String breedName;

        public PetInfo(PetEntity pet) {
            this.id = pet.getId();
            this.name = pet.getName();
            this.vaccinated = pet.isVaccinated();
            this.species = pet.getSpecies();
            this.breedName = pet.getBreedName();
        }
    }

    @Getter
    public static class CustomerPhoneInfo {
        private final Long id;
        private final String label;
        private final String phoneNumber;
        private final PhoneType phoneType;

        public CustomerPhoneInfo(CustomerPhoneEntity customerPhone) {
            this.id = customerPhone.getId();
            this.label = customerPhone.getLabel();
            this.phoneNumber = customerPhone.getPhoneNumber();
            this.phoneType = customerPhone.getPhoneType();
        }
    }
}