package com.dangdang.check.interfaces.customer.response;

import com.dangdang.check.domain.customer.response.CustomerInfo;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.dangdang.check.domain.customer.response.CustomerInfo.*;

@Getter
public class RegisterCustomerWithPetsResponse {

    private final Long id;
    private final String name;
    private final String specialNotes;
    private final List<CustomerPhoneResponse> customerPhones;
    private final List<PetResponse> pets;

    public RegisterCustomerWithPetsResponse(CustomerInfo customerInfo) {
        this.id = customerInfo.getId();
        this.name = customerInfo.getName();
        this.specialNotes = customerInfo.getSpecialNotes();
        this.customerPhones = customerInfo.getPhones() != null
                ? customerInfo.getPhones().stream()
                .map(CustomerPhoneResponse::new)
                .collect(Collectors.toList())
                : Collections.emptyList();
        this.pets = customerInfo.getPets() != null
                ? customerInfo.getPets().stream()
                .map(PetResponse::new)
                .collect(Collectors.toList())
                : Collections.emptyList();
    }

    @Getter
    public static class CustomerPhoneResponse {
        private final Long id;
        private final String label;
        private final String phoneNumber;
        private final String phoneType;

        public CustomerPhoneResponse(CustomerPhoneInfo customerPhoneInfo) {
            this.id = customerPhoneInfo.getId();
            this.label = customerPhoneInfo.getLabel();
            this.phoneNumber = customerPhoneInfo.getPhoneNumber();
            this.phoneType = customerPhoneInfo.getPhoneType().name();
        }
    }
    @Getter
    public static class PetResponse {
        private final Long id;
        private final String name;
        private final Boolean vaccinated;
        private final String species;
        private final String breedName;

        public PetResponse(PetInfo petInfo) {
            this.id = petInfo.getId();
            this.name = petInfo.getName();
            this.vaccinated = petInfo.getVaccinated();
            this.species = petInfo.getSpecies();
            this.breedName = petInfo.getBreedName();
        }
    }


}
