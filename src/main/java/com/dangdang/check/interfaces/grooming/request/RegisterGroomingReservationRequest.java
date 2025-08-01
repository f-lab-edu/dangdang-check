package com.dangdang.check.interfaces.grooming.request;

import com.dangdang.check.domain.grooming.request.RegisterGroomingReservation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class RegisterGroomingReservationRequest {
    @NotBlank(message = "제목은 필수 입력 항목입니다.")
    @Size(max = 30, message = "제목은 최대 30자까지 입력 가능합니다.")
    private String title;

    @Size(max = 500, message = "요청사항은 최대 500자까지 입력 가능합니다.")
    private String groomingRequest;

    @NotNull(message = "시작 시간은 필수 입력 항목입니다.")
    private LocalDateTime startAt;

    @NotNull(message = "종료 시간은 필수 입력 항목입니다.")
    private LocalDateTime endAt;

    @NotEmpty(message = "최소한 한 마리 이상의 반려견을 선택해야 합니다.")
    private List<Long> petIds;


    public RegisterGroomingReservation toCommand(String loginId) {
        return RegisterGroomingReservation.builder()
                .employeeLoginId(loginId)
                .title(title)
                .groomingRequest(groomingRequest)
                .startAt(startAt)
                .endAt(endAt)
                .petIds(petIds)
                .build();
    }

}
