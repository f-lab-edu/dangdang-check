package com.dangdang.check.common.util;

public class ErrorMessageUtil {

    private static final String EXCEPTION_TEMPLATE =
            "[예외 발생]\n" +
                    "📅 날짜 : %s\n" +
                    "📍 위치 : %s\n" +
                    "🔢 매개변수 : [%s]\n" +
                    "❗ 원인 : %s";

    public static String formatExceptionMessage(String method, String args, Throwable t) {
        return String.format(
                EXCEPTION_TEMPLATE,
                java.time.LocalDateTime.now(),
                method,
                args,
                t.toString()
        );
    }
}