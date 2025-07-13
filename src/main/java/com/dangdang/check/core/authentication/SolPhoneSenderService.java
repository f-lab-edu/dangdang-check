package com.dangdang.check.core.authentication;

import com.dangdang.check.common.config.SolApiProperties;
import com.dangdang.check.domain.verification.PhoneSenderService;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class SolPhoneSenderService implements PhoneSenderService {

    private final DefaultMessageService messageService;
    private final SolApiProperties solApiProperties;

    public SolPhoneSenderService(SolApiProperties solApiProperties) {
        this.messageService = NurigoApp.INSTANCE.initialize(
                solApiProperties.getApiKey(),
                solApiProperties.getApiSecret(),
                solApiProperties.getBaseUrl()
        );
        this.solApiProperties = solApiProperties;
    }

    @Async
    @Override
    public CompletableFuture<Boolean> send(String to, String text) {
        try {
            messageService.send(buildMessage(to, text));
            return CompletableFuture.completedFuture(Boolean.TRUE);
        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    private Message buildMessage(String to, String text) {
        Message message = new Message();
        message.setFrom(solApiProperties.getFromNumber());
        message.setTo(to);
        message.setText(text);
        return message;
    }
}
