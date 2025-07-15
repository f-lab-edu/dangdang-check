package com.dangdang.check.core.notification;

import com.dangdang.check.common.config.SlackWebhookProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class SlackNotifierService {

    private final SlackWebhookProperties slackWebhookProperties;
    private final RestTemplate restTemplate = new RestTemplate();

    public void sendErrorMessage(String message) {
        sendMessage(slackWebhookProperties.getErrorUrl(), message);
    }

    public void sendMessage(String webhookUrl, String message) {
        if (webhookUrl == null || webhookUrl.isBlank()) return;

        String payload = "{\"text\": \"" + message.replace("\"", "\\\"") + "\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        HttpEntity<String> request = new HttpEntity<>(payload, headers);
        restTemplate.postForObject(webhookUrl, request, String.class);
    }
}
