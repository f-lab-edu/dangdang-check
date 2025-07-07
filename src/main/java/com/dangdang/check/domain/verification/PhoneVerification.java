package com.dangdang.check.domain.verification;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

import static com.dangdang.check.common.constant.VerificationMessageConstants.CODE_VALID_SECONDS;

@Getter
@RedisHash(value = "phoneVerification", timeToLive = CODE_VALID_SECONDS)
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class PhoneVerification {

    @Id
    private String mobilePhone;
    private String code;
    private int failCount = 0;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime lastSentAt = LocalDateTime.now();

    public PhoneVerification(String mobilePhone, String code) {
        this.mobilePhone = mobilePhone;
        this.code = code;
    }

    public void modifyFailCount(int failCount) {
        this.failCount = failCount;
    }
}