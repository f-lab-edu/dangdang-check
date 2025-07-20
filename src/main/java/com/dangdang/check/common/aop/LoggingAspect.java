package com.dangdang.check.common.aop;

import com.dangdang.check.common.util.ErrorMessageUtil;
import com.dangdang.check.core.notification.SlackNotifierService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class LoggingAspect {

    private final SlackNotifierService slackNotifierService;
    private final ThreadLocal<Integer> callDepth = ThreadLocal.withInitial(() -> 0);
    private final ThreadLocal<String> firstExceptionMethod = new ThreadLocal<>();
    private static final int ALIGN_WIDTH = 60;

    @Around("execution(* com.dangdang.check.core..*(..)) || " +
            "execution(* com.dangdang.check.domain..*(..)) || " +
            "execution(* com.dangdang.check.interfaces..*(..))")
    public Object logExecutionDetails(ProceedingJoinPoint joinPoint) throws Throwable {
        int depth = callDepth.get();
        callDepth.set(depth + 1);

        String methodLabel = getMethodLabel(joinPoint);
        String indentedMethodLabel = formatMethodWithIndent(methodLabel, depth);
        String args = getArgumentsAsString(joinPoint);

        log.info("{}args: [{}]", indentedMethodLabel, args);

        try {
            Object result = joinPoint.proceed();
            log.info("{}return: {}", indentedMethodLabel, result);
            return result;
        } catch (Throwable t) {
            if (firstExceptionMethod.get() == null) {
                firstExceptionMethod.set(methodLabel);
            }

            String errorMessage = ErrorMessageUtil.formatExceptionMessage(firstExceptionMethod.get(), args, t);

            if (depth == 0) {
                log.error("{}throw: {}", indentedMethodLabel, t.getMessage(), t);
                slackNotifierService.sendErrorMessage(errorMessage);
            } else {
                log.info("{}rethrow: {}", indentedMethodLabel, t.getMessage());
            }
            throw t;
        } finally {
            if (depth <= 0) {
                cleanThreadLocals();
            } else {
                callDepth.set(depth - 1);
            }
        }

    }

    private void cleanThreadLocals() {
        firstExceptionMethod.remove();
        callDepth.remove();
    }

    private String getMethodLabel(ProceedingJoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        return className + "." + methodName + "()";
    }

    private String getArgumentsAsString(ProceedingJoinPoint joinPoint) {
        return Arrays.stream(joinPoint.getArgs())
                .map(arg -> arg == null ? "null" : arg.toString())
                .collect(Collectors.joining(", "));
    }

    private String getIndent(int level) {
        return "    ".repeat(level);
    }

    private String formatMethodWithIndent(String classMethod, int depth) {
        String base = getIndent(depth) + classMethod;
        int pad = Math.max(0, ALIGN_WIDTH - base.length());
        return base + " ".repeat(pad);
    }
}
