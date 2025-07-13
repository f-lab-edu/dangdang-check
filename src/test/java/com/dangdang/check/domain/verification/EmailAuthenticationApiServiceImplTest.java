package com.dangdang.check.domain.verification;

import com.dangdang.check.common.constant.VerificationMessageConstants;
import com.dangdang.check.core.employee.EmployeeFindService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailAuthenticationApiServiceImplTest {
    @Mock
    private EmployeeFindService employeeFindService;

    @Mock
    private EmailVerificationService emailVerificationService;

    @Mock
    private EmailSenderService emailSenderService;

    @InjectMocks
    private EmailAuthenticationApiServiceImpl emailAuthenticationApiService;

    @Test
    void sendVerificationCode_success() {
        // given
        String email = "test@example.com";
        String code = "123456";

        when(employeeFindService.existsByEmail(email)).thenReturn(false);
        when(emailVerificationService.createAndSaveCode(email)).thenReturn(code);
        when(emailSenderService.send(eq(email), anyString(), anyString()))
                .thenReturn(CompletableFuture.completedFuture(true));

        // when
        boolean result = emailAuthenticationApiService.sendVerificationCode(email);

        // then
        assertTrue(result);

        verify(employeeFindService).existsByEmail(email);
        verify(emailVerificationService).createAndSaveCode(email);
        verify(emailSenderService).send(eq(email), eq(VerificationMessageConstants.SUBJECT),
                eq(String.format(VerificationMessageConstants.BODY_TEMPLATE, code)));
    }

    @Test
    void sendVerificationCode_emailAlreadyExists_throwsException() {
        // given
        String email = "existing@example.com";
        when(employeeFindService.existsByEmail(email)).thenReturn(true);

        // when / then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            emailAuthenticationApiService.sendVerificationCode(email);
        });

        assertEquals("이미 존재하는 email입니다.", exception.getMessage());

        verify(employeeFindService).existsByEmail(email);
        verifyNoInteractions(emailVerificationService);
        verifyNoInteractions(emailSenderService);
    }



}