package com.dangdang.check.interfaces.grooming.response;

import com.dangdang.check.domain.grooming.response.GroomingReservationInfo;
import com.dangdang.check.domain.grooming.response.GroomingReservationInfo.CustomerInfo;
import com.dangdang.check.domain.grooming.response.GroomingReservationInfo.PetInfo;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class RegisterGroomingReservationResponse {

    private final Long id;
    private final String title;
    private final String groomingRequest;
    private final LocalDateTime startAt;
    private final LocalDateTime endAt;
    private final String reservationStatus;
    private final CustomerResponse customerResponse;
    private final List<PetResponse> petResponses;

    public RegisterGroomingReservationResponse(GroomingReservationInfo groomingReservationInfo) {
        this.id = groomingReservationInfo.getId();
        this.title = groomingReservationInfo.getTitle();
        this.groomingRequest = groomingReservationInfo.getGroomingRequest();
        this.startAt = groomingReservationInfo.getStartAt();
        this.endAt = groomingReservationInfo.getEndAt();
        this.reservationStatus = groomingReservationInfo.getReservationStatus().name();
        this.customerResponse = new CustomerResponse(groomingReservationInfo.getCustomerInfo());
        this.petResponses = groomingReservationInfo.getPetInfos().stream()
                .map(PetResponse::new)
                .toList();
    }

    @Getter
    public static class CustomerResponse {
        private final Long id;
        private final String name;

        public CustomerResponse(CustomerInfo customerInfo) {
            this.id = customerInfo.getId();
            this.name = customerInfo.getName();
        }
    }

    @Getter
    public static class PetResponse {
        private final Long id;
        private final String name;

        public PetResponse(PetInfo petInfo) {
            this.id = petInfo.getId();
            this.name = petInfo.getName();
        }
    }
}