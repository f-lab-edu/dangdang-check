package com.dangdang.check.domain.customer;

import com.dangdang.check.domain.BaseEntity;
import com.dangdang.check.domain.pet.PetEntity;
import com.dangdang.check.domain.store.StoreEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "customers")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomerEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Lob
    private String specialNotes;
    private boolean isDeleted = false;
    private LocalDateTime deletedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", nullable = false)
    private StoreEntity store;

    @OneToMany(mappedBy = "customer")
    private List<CustomerPhoneEntity> customerPhones = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<PetEntity> pets = new ArrayList<>();

    @Builder
    public CustomerEntity(String name, String specialNotes) {
        if(isNotValidParams(name)){
            throw new InvalidParameterException();
        }
        this.name = name;
        this.specialNotes = specialNotes;
    }

    public void addPet(PetEntity pet) {
        pets.add(pet);
        pet.modifyCustomer(this);
    }

    public void addCustomerPhone(CustomerPhoneEntity phone) {
        customerPhones.add(phone);
        phone.modifyCustomer(this);
    }

    public void modifyStore(StoreEntity store) {
        this.store = store;
    }

    private boolean isNotValidParams(String name) {
        return !StringUtils.hasText(name);
    }
}