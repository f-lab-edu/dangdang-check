package com.dangdang.check.domain.verification;

import java.util.concurrent.CompletableFuture;

public interface EmailSenderService {

    CompletableFuture<Boolean> send(String to, String subject, String text);
}
