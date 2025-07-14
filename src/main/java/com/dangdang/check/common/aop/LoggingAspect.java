package com.dangdang.check.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    private ThreadLocal<Integer> callDepth = ThreadLocal.withInitial(() -> 0);
    private static final int ALIGN_WIDTH = 60;

    @Around("execution(* com.dangdang.check.core..*(..)) || " +
            "execution(* com.dangdang.check.domain..*(..)) || " +
            "execution(* com.dangdang.check.interfaces..*(..))")
    public Object logExecutionDetails(ProceedingJoinPoint joinPoint) throws Throwable {
        int depth = callDepth.get();
        callDepth.set(depth + 1);

        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String method = className + "." + methodName + "()";

        String args = Arrays.stream(joinPoint.getArgs())
                .map(arg -> arg == null ? "null" : arg.toString())
                .collect(Collectors.joining(", "));

        log.info("{}args: [{}]", formatLeft(method, depth), args);

        try {
            Object result = joinPoint.proceed();
            log.info("{}return: {}", formatLeft(method, depth), result);
            return result;
        } catch (Throwable t) {
            if (depth == 0) {
                log.error("{}throw: {}", formatLeft(method, depth), t.getMessage(), t);
            } else {
                log.info("{}rethrow: {}", formatLeft(method, depth), t.getMessage());
            }
            throw t;
        } finally {
            if (depth <= 0) {
                callDepth.remove();
            } else {
                callDepth.set(depth - 1);
            }
        }

    }

    private String indent(int level) {
        return "    ".repeat(level); // 4 spaces per depth
    }

    private String formatLeft(String classMethod, int depth) {
        String base = indent(depth) + classMethod;
        int pad = Math.max(0, ALIGN_WIDTH - base.length());
        return base + " ".repeat(pad);
    }
}
