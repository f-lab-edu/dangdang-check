package com.dangdang.check.domain.grooming;

import com.dangdang.check.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "grooming_record_images")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroomingRecordImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalFileName; // 원본 파일 이름
    private String storedFileName; // 저장된 파일 이름
    private String url; // 이미지 URL

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grooming_record_id", nullable = false)
    private GroomingRecord groomingRecord;
}
