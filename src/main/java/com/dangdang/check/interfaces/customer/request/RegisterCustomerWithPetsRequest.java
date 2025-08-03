package com.dangdang.check.interfaces.customer.request;

import com.dangdang.check.domain.customer.PhoneType;
import com.dangdang.check.domain.customer.request.RegisterCustomerWithPets;
import com.dangdang.check.domain.pet.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.dangdang.check.domain.customer.request.RegisterCustomerWithPets.*;

@Getter
public class RegisterCustomerWithPetsRequest {

    @NotBlank(message = "고객 이름은 필수입니다.")
    private String customerName;

    @NotBlank(message = "휴대폰 번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^\\d{10,11}$", message = "휴대폰 번호는 10~11자리 숫자로 입력해주세요.")
    private String phoneNumber;

    @NotBlank(message = "전화 라벨은 필수입니다.")
    private String phoneLabel;

    @NotNull(message = "전화 타입은 필수입니다.")
    private PhoneType phoneType;

    private String specialNotes;

    @NotEmpty(message = "반려동물 정보는 최소 한 마리 이상 입력해야 합니다.")
    private List<@Valid RegisterPetRequest> pets;

    public RegisterCustomerWithPets toCommand(String loginId) {
        return builder()
                .loginId(loginId)
                .customerName(customerName)
                .phoneNumber(phoneNumber)
                .phoneLabel(phoneLabel)
                .phoneType(phoneType)
                .specialNotes(specialNotes)
                .pets(pets != null ?
                        pets.stream()
                                .map(RegisterPetRequest::toCommand)
                                .collect(Collectors.toList()) : Collections.emptyList())
                .build();
    }

    @Getter
    public static class RegisterPetRequest {

        @NotBlank(message = "반려동물 이름은 필수입니다.")
        private String name;

        private Gender gender;

        @Past(message = "생일은 과거 날짜여야 합니다.")
        private LocalDate birthday;

        private Boolean neutered;

        @NotNull(message = "예방접종 여부를 입력해주세요.")
        private Boolean vaccinated;

        private String specialNotes;

        @DecimalMin(value = "0.1", message = "몸무게는 0.1kg 이상이어야 합니다.")
        private Double weight;

        @NotBlank(message = "품종명을 입력해주세요.")
        private String breedName;

        @NotBlank(message = "종(species)을 입력해주세요.")
        private String species;

        public RegisterPet toCommand() {
            return RegisterPet.builder()
                    .name(name)
                    .gender(gender)
                    .birthday(birthday)
                    .neutered(neutered)
                    .vaccinated(vaccinated)
                    .specialNotes(specialNotes)
                    .weight(weight)
                    .breedName(breedName)
                    .species(species)
                    .build();
        }

    }
}
