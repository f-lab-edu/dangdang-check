package com.dangdang.check.interfaces.grooming;

import com.dangdang.check.common.argumentresolver.Login;
import com.dangdang.check.common.response.CommonResponse;
import com.dangdang.check.domain.grooming.GroomingReservationApiService;
import com.dangdang.check.domain.grooming.request.RegisterGroomingReservation;
import com.dangdang.check.domain.grooming.response.GroomingReservationInfo;
import com.dangdang.check.interfaces.grooming.request.RegisterGroomingReservationRequest;
import com.dangdang.check.interfaces.grooming.response.RegisterGroomingReservationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GroomingReservationApiController {

    private final GroomingReservationApiService groomingReservationService;

    @PostMapping("/api/grooming-reservations")
    public CommonResponse<RegisterGroomingReservationResponse> registerGroomingReservation(@Login String loginId,
                                                                                           @RequestBody @Valid RegisterGroomingReservationRequest request) {
        RegisterGroomingReservation command = request.toCommand(loginId);
        GroomingReservationInfo groomingReservationInfo = groomingReservationService.registerGroomingReservation(command);
        return CommonResponse.success(new RegisterGroomingReservationResponse(groomingReservationInfo));
    }
}
