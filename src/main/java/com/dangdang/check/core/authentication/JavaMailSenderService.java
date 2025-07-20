package com.dangdang.check.core.authentication;

import com.dangdang.check.domain.verification.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JavaMailSenderService implements EmailSenderService {

    private final JavaMailSender javaMailSender;

    @Override
    public void send(String to, String subject, String text) {
        try {
            javaMailSender.send(buildMessage(to, subject, text));
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email", e);
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
