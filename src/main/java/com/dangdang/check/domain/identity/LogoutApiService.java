package com.dangdang.check.domain.identity;

public interface LogoutApiService {

    void logout(String refreshToken);
}