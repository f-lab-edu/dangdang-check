package com.dangdang.check.domain.grooming;

import com.dangdang.check.domain.customer.CustomerEntity;
import com.dangdang.check.domain.grooming.request.RegisterGroomingReservation;

public class GroomingReservationEntityFactory {

    public static GroomingReservationEntity from(RegisterGroomingReservation command, CustomerEntity customer) {
        return GroomingReservationEntity.builder()
                .startAt(command.getStartAt())
                .endAt(command.getEndAt())
                .title(command.getTitle())
                .groomingRequest(command.getGroomingRequest())
                .customer(customer)
                .build();
    }

}
