package com.dangdang.check.domain.verification;


public interface PhoneSenderService {
    void send(String to, String text);
}
