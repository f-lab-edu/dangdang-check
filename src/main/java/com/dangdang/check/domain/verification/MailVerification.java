package com.dangdang.check.domain.verification;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@RedisHash(value = "emailVerification", timeToLive = 300)
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class MailVerification {

    @Id
    private String email;
    private String code;
    private int failCount = 0;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime lastSentAt = LocalDateTime.now();

    public MailVerification(String email, String code) {
        this.email = email;
        this.code = code;
    }
}