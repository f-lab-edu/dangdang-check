package com.dangdang.check.domain.verification;


public interface EmailSenderService {

    void send(String to, String subject, String text);
}
