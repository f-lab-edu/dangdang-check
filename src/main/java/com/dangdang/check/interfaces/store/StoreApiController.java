package com.dangdang.check.interfaces.store;


import com.dangdang.check.common.response.CommonResponse;
import com.dangdang.check.domain.store.StoreService;
import com.dangdang.check.domain.store.request.GetStoresByCriteria;
import com.dangdang.check.domain.store.response.StoreInfo;
import com.dangdang.check.domain.store.response.StoreSummaryInfo;
import com.dangdang.check.interfaces.store.request.GetStoresByCriteriaRequest;
import com.dangdang.check.interfaces.store.request.RegisterStoreRequest;
import com.dangdang.check.interfaces.store.response.GetStoresByCriteriaResponse;
import com.dangdang.check.interfaces.store.response.RegisterStoreResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StoreApiController {

    private final StoreService storeService;

    @PostMapping("/api/store-requests")
    public CommonResponse<RegisterStoreResponse> registerStore(@RequestBody @Valid RegisterStoreRequest request) {
        StoreInfo storeInfo = storeService.registerStore(request.toCommand());
        return CommonResponse.success(new RegisterStoreResponse(storeInfo));
    }

    @GetMapping("/api/stores")
    public CommonResponse<Page<GetStoresByCriteriaResponse>> getStoresByCriteria(@Valid GetStoresByCriteriaRequest request, Pageable pageable) {
        GetStoresByCriteria criteria = request.toCriteria(pageable);
        Page<StoreSummaryInfo> storeSummaryPage = storeService.getStoresByCriteria(criteria);
        Page<GetStoresByCriteriaResponse> response = storeSummaryPage.map(GetStoresByCriteriaResponse::new);
        return CommonResponse.success(response);
    }
}