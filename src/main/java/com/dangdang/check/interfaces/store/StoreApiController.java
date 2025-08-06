package com.dangdang.check.interfaces.store;


import com.dangdang.check.common.argumentresolver.Login;
import com.dangdang.check.common.response.CommonResponse;
import com.dangdang.check.domain.store.StoreApiService;
import com.dangdang.check.domain.store.request.GetStoresByCriteria;
import com.dangdang.check.domain.store.response.StoreInfo;
import com.dangdang.check.domain.store.response.StoreSummaryInfo;
import com.dangdang.check.interfaces.store.request.GetStoresByCriteriaRequest;
import com.dangdang.check.interfaces.store.request.RegisterStoreRequest;
import com.dangdang.check.interfaces.store.response.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StoreApiController {

    private final StoreApiService StoreApiService;

    @PostMapping("/api/store-requests")
    public CommonResponse<RegisterStoreResponse> registerStore(@RequestBody @Valid RegisterStoreRequest request,
                                                               @Login String loginId) {
        StoreInfo storeInfo = StoreApiService.registerStore(request.toCommand(loginId));
        return CommonResponse.success(new RegisterStoreResponse(storeInfo));
    }

    @GetMapping("/api/stores/{storeId}")
    public CommonResponse<GetStoreByIdResponse> getStoreById(@PathVariable Long storeId) {
        StoreInfo storeInfo = StoreApiService.getStoreById(storeId);
        return CommonResponse.success(new GetStoreByIdResponse(storeInfo));
    }

    @GetMapping("/api/stores")
    public CommonResponse<Page<GetStoresByCriteriaResponse>> getStoresByCriteria(@Valid GetStoresByCriteriaRequest request, Pageable pageable) {
        GetStoresByCriteria criteria = request.toCriteria(pageable);
        Page<StoreSummaryInfo> storeSummaryPage = StoreApiService.getStoresByCriteria(criteria);
        Page<GetStoresByCriteriaResponse> response = storeSummaryPage.map(GetStoresByCriteriaResponse::new);
        return CommonResponse.success(response);
    }

    @PatchMapping("/api/stores/{storeId}/approve")
    public CommonResponse<ApproveStoreResponse> approveStore(@PathVariable Long storeId) {
        StoreInfo storeInfo = StoreApiService.approveStore(storeId);
        return CommonResponse.success(new ApproveStoreResponse(storeInfo));
    }

    @PatchMapping("/api/stores/{storeId}/reject")
    public CommonResponse<RejectStoreResponse> rejectStore(@PathVariable Long storeId, @RequestBody String rejectedReason) {
        StoreInfo storeInfo = StoreApiService.rejectStore(storeId, rejectedReason);
        return CommonResponse.success(new RejectStoreResponse(storeInfo));
    }
}