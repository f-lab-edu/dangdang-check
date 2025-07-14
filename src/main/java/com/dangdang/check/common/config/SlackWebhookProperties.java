package com.dangdang.check.common.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component
@ConfigurationProperties(prefix = "slack.webhook")
public class SlackWebhookProperties {
    private String errorUrl;
}
