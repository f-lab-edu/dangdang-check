package com.dangdang.check.domain.pet;

import com.dangdang.check.domain.BaseEntity;
import com.dangdang.check.domain.customer.CustomerEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "pets")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PetEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private boolean neutered; // 중성화 여부
    private boolean vaccinated; // 예방접종 여부
    private Double weight;
    private LocalDate birthday; // 생년월일
    private String specialNotes; // 특이사항
    private String species; // 예: "개", "고양이"
    private String breedName; // 품종 이름
    private boolean isDeleted = false;
    private LocalDateTime deletedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    @Builder
    public PetEntity(String name, Gender gender, Boolean neutered, Boolean vaccinated, String specialNotes, LocalDate birthday, Double weight, String species, String breedName) {
        if(isNotValidParams(name, vaccinated, species, breedName)){
            throw new InvalidParameterException();
        }

        this.name = name;
        this.gender = gender;
        this.neutered = neutered;
        this.vaccinated = vaccinated;
        this.specialNotes = specialNotes;
        this.birthday = birthday;
        this.weight = weight;
        this.species = species;
        this.breedName = breedName;
    }

    public void modifyCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    private boolean isNotValidParams(String name, Boolean vaccinated, String species, String breedName) {
        return !StringUtils.hasText(name) ||
                vaccinated == null ||
                !StringUtils.hasText(species) ||
                !StringUtils.hasText(breedName);
    }

}
