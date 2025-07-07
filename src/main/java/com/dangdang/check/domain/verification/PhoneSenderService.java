package com.dangdang.check.domain.verification;

import java.util.concurrent.CompletableFuture;

public interface PhoneSenderService {
    CompletableFuture<Boolean> send(String to, String text);
}
