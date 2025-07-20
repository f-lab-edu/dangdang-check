package com.dangdang.check.interfaces.store.request;

import com.dangdang.check.domain.store.request.RegisterStore;
import com.dangdang.check.domain.store.request.RegisterStore.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RegisterStoreRequest {

    @NotBlank(message = "loginId는 필수값입니다.")
    @Size(min = 5, max = 15, message = "loginId는 최소 5자, 최대 15자이어야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "loginId는 영문자와 숫자만 허용됩니다.")
    private String loginId;

    @NotBlank(message = "가게 이름(storeName)은 필수값입니다.")
    @Size(min = 1, max = 30, message = "가게 이름은 1자 이상 30자 이하로 입력해주세요.")
    private String storeName;

    @NotBlank(message = "email은 필수값입니다.")
    @Email(message = "유효한 이메일 형식이어야 합니다.")
    private String email;

    @NotBlank(message = "대표 전화번호(mainPhone)는 필수값입니다.")
    @Pattern(regexp = "^\\d{10,11}$", message = "전화번호는 10~11자리 숫자로 입력해주세요.")
    private String mainPhone;

    @Valid
    @NotNull(message = "가게 주소(storeAddress)는 필수값입니다.")
    private AddressRequest storeAddress;

    @NotBlank(message = "사업자명(businessName)은 필수값입니다.")
    @Size(min = 1, max = 50, message = "사업자명은 1자 이상 50자 이하로 입력해주세요.")
    private String businessName;

    @NotBlank(message = "사업자등록번호(businessRegistrationNumber)는 필수값입니다.")
    @Pattern(regexp = "^\\d{10}$", message = "사업자등록번호는 10자리 숫자여야 합니다.")
    private String businessRegistrationNumber;

    @NotBlank(message = "업종(businessType)은 필수값입니다.")
    @Size(min = 1, max = 30, message = "업종은 1자 이상 30자 이하로 입력해주세요.")
    private String businessType;

    @NotBlank(message = "대표자명(representativeName)은 필수값입니다.")
    @Size(min = 2, max = 20, message = "대표자명은 2자 이상 20자 이하로 입력해주세요.")
    private String representativeName;

    @Valid
    @NotNull(message = "사업장 주소(businessAddress)는 필수값입니다.")
    private AddressRequest businessAddress;

    public RegisterStore toCommand() {
        return RegisterStore.builder()
                .loginId(loginId)
                .storeName(storeName)
                .email(email)
                .mainPhone(mainPhone)
                .storeAddress(storeAddress.toCommand())
                .businessName(businessName)
                .businessRegistrationNumber(businessRegistrationNumber)
                .businessType(businessType)
                .representativeName(representativeName)
                .businessAddress(businessAddress.toCommand())
                .build();
    }

    @Getter
    @ToString
    public static class AddressRequest {

        @NotBlank(message = "우편번호(zipCode)는 필수값입니다.")
        private String zipCode;

        @NotBlank(message = "시/도(state)는 필수값입니다.")
        private String state;

        @NotBlank(message = "시/군/구(city)는 필수값입니다.")
        private String city;

        @NotBlank(message = "도로명(street)은 필수값입니다.")
        private String street;

        @Size(max = 50, message = "상세주소(detail)는 50자 이하로 입력해주세요.")
        private String detail;

        public Address toCommand() {
            return Address.builder()
                    .zipCode(zipCode)
                    .state(state)
                    .city(city)
                    .street(street)
                    .detail(detail)
                    .build();
        }
    }
}