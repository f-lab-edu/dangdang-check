package com.dangdang.check.domain.store;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.InvalidParameterException;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {
    private String zipCode; // 우편번호
    private String state; // 시/도
    private String city; // 시/군/구
    private String street; // 도로명 또는 지번 주소
    private String detail; // 상세 주소 (건물명, 동/호수 등)

    public Address(String zipCode, String state, String city, String street, String detail) {
        if(isNotValidParams(zipCode, state, city, street)){
            throw new InvalidParameterException();
        }
        this.zipCode = zipCode;
        this.state = state;
        this.city = city;
        this.street = street;
        this.detail = detail;
    }

    private boolean isNotValidParams(String zipCode, String state, String city, String street) {
        return StringUtils.isBlank(zipCode)
                || StringUtils.isBlank(state)
                || StringUtils.isBlank(city)
                || StringUtils.isBlank(street);
    }
}
