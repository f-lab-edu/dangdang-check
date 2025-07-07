package com.dangdang.check.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "solapi")
public class SolApiProperties {
    private String apiKey;
    private String apiSecret;
    private String baseUrl;
    private String fromNumber;
}