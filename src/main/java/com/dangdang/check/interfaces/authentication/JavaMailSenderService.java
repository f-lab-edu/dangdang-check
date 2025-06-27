package com.dangdang.check.interfaces.authentication;

import com.dangdang.check.domain.verification.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class JavaMailSenderService implements MailSenderService {

    private final JavaMailSender javaMailSender;

    @Async
    @Override
    public CompletableFuture<Boolean> send(String to, String subject, String text) {
        try {
            javaMailSender.send(buildMessage(to, subject, text));
            return CompletableFuture.completedFuture(Boolean.TRUE);
        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    private SimpleMailMessage buildMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        return message;
    }
}
