package com.dangdang.check.common.advice;

import com.dangdang.check.common.response.CommonResponse;
import com.dangdang.check.common.response.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class CommonControllerAdvice {

    /**
     * Handles IllegalArgumentException.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public CommonResponse<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        if (ex.getMessage() == null) {
            return CommonResponse.fail(ErrorCode.COMMON_ILLEGAL_STATUS);
        }
        log.error("IllegalArgumentException: {}", ex.getMessage());
        return CommonResponse.fail(ex.getMessage(), ErrorCode.COMMON_ILLEGAL_STATUS.name());
    }

    /**
     * Handles DataIntegrityViolationException.
     */
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public CommonResponse<?> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        if (ex.getMessage() == null) {
            return CommonResponse.fail(ErrorCode.COMMON_DUPLICATE_RESOURCE);
        }
        log.error("DataIntegrityViolationException: {}", ex.getMessage());
        return CommonResponse.fail("중복된 값이 존재합니다.", ErrorCode.COMMON_DUPLICATE_RESOURCE.name());
    }

    /**
     * Handles MethodArgumentNotValidException.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResponse<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
        log.error("MethodArgumentNotValidException: {}", errorMessage);
        return CommonResponse.fail(errorMessage, ErrorCode.COMMON_INVALID_PARAMETER.name());
    }


}
