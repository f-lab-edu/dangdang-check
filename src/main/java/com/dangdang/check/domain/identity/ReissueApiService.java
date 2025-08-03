package com.dangdang.check.domain.identity;

import com.dangdang.check.domain.identity.response.ReissueTokenInfo;

public interface ReissueApiService {

    ReissueTokenInfo reissueToken(String refreshToken);
}
