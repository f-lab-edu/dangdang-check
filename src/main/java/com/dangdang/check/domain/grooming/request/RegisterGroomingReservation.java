package com.dangdang.check.domain.grooming.request;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class RegisterGroomingReservation {

    private final String employeeLoginId;
    private final String title;
    private final String groomingRequest;
    private final LocalDateTime startAt;
    private final LocalDateTime endAt;
    private final List<Long> petIds;

    @Builder
    public RegisterGroomingReservation(String employeeLoginId, String title, String groomingRequest, LocalDateTime startAt, LocalDateTime endAt, List<Long> petIds) {
        this.employeeLoginId = employeeLoginId;
        this.title = title;
        this.groomingRequest = groomingRequest;
        this.startAt = startAt;
        this.endAt = endAt;
        this.petIds = petIds;
    }
}
