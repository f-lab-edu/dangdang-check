package com.dangdang.check.domain.pet;

import com.dangdang.check.domain.BaseEntity;
import com.dangdang.check.domain.customer.CustomerEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
}
