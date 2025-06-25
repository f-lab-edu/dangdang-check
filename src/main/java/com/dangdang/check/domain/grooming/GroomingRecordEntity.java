package com.dangdang.check.domain.grooming;

import com.dangdang.check.domain.BaseEntity;
import com.dangdang.check.domain.employee.EmployeeEntity;
import com.dangdang.check.domain.pet.PetEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "grooming_records")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroomingRecordEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String styleTag; // 예: '전체 3mm 테디베어컷'
    @Lob
    String specialNotes; // 미용 특이사항 및 상세기록
    LocalDateTime groomedAt; // 미용한 시간
    private Double weight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    PetEntity pet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    EmployeeEntity employee; // 미용 담당 직원

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grooming_reservation_id")
    private GroomingReservationEntity groomingReservation; // 미용 예약 정보

}