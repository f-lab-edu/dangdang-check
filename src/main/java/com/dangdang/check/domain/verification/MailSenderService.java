package com.dangdang.check.domain.verification;

import java.util.concurrent.CompletableFuture;

public interface MailSenderService {

    CompletableFuture<Boolean> send(String to, String subject, String text);
}
