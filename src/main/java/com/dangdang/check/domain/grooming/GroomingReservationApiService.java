package com.dangdang.check.domain.grooming;

import com.dangdang.check.domain.grooming.request.RegisterGroomingReservation;
import com.dangdang.check.domain.grooming.response.GroomingReservationInfo;

public interface GroomingReservationApiService {

    GroomingReservationInfo registerGroomingReservation(RegisterGroomingReservation command);
}
