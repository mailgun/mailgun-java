package com.mailgun.api.v5;

import com.mailgun.api.MailgunApi;
import com.mailgun.enums.ApiVersion;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.custommessagelimit.CustomMessageLimitResponse;
import com.mailgun.model.custommessagelimit.CustomMessageLimitSuccessResponse;
import com.mailgun.model.subaccounts.DisableSubaccountQuery;
import com.mailgun.model.subaccounts.SubaccountFeaturesResponse;
import com.mailgun.model.subaccounts.SubaccountResponse;
import com.mailgun.model.subaccounts.SubaccountsListQuery;
import com.mailgun.model.subaccounts.SubaccountsListResponse;
import com.mailgun.model.subaccounts.UpdateSubaccountFeaturesRequest;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import feign.Response;

import java.math.BigDecimal;

import static com.mailgun.util.Constants.HEADER_ON_BEHALF_OF;

/**
 * Subaccounts API. Supports creating, managing, and deleting child accounts.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/subaccounts">Subaccounts</a>
 */
@Headers("Accept: application/json")
public interface MailgunSubaccountsApi extends MailgunApi {

    static ApiVersion getApiVersion() {
        return ApiVersion.V_5;
    }

    @RequestLine("GET /accounts/subaccounts/{subaccount_id}")
    SubaccountResponse getSubaccount(@Param("subaccount_id") String subaccountId);

    @RequestLine("GET /accounts/subaccounts/{subaccount_id}")
    Response getSubaccountFeignResponse(@Param("subaccount_id") String subaccountId);

    @RequestLine("GET /accounts/subaccounts")
    SubaccountsListResponse listSubaccounts();

    @RequestLine("GET /accounts/subaccounts")
    Response listSubaccountsFeignResponse();

    @RequestLine("GET /accounts/subaccounts")
    SubaccountsListResponse listSubaccounts(@QueryMap SubaccountsListQuery query);

    @RequestLine("GET /accounts/subaccounts")
    Response listSubaccountsFeignResponse(@QueryMap SubaccountsListQuery query);

    @RequestLine("POST /accounts/subaccounts?name={name}")
    SubaccountResponse createSubaccount(@Param("name") String name);

    @RequestLine("POST /accounts/subaccounts?name={name}")
    Response createSubaccountFeignResponse(@Param("name") String name);

    @Headers(HEADER_ON_BEHALF_OF + ": {subaccount_id}")
    @RequestLine("DELETE /accounts/subaccounts")
    ResponseWithMessage deleteSubaccount(@Param("subaccount_id") String subaccountId);

    @Headers(HEADER_ON_BEHALF_OF + ": {subaccount_id}")
    @RequestLine("DELETE /accounts/subaccounts")
    Response deleteSubaccountFeignResponse(@Param("subaccount_id") String subaccountId);

    @RequestLine("POST /accounts/subaccounts/{subaccount_id}/disable")
    SubaccountResponse disableSubaccount(@Param("subaccount_id") String subaccountId);

    @RequestLine("POST /accounts/subaccounts/{subaccount_id}/disable")
    Response disableSubaccountFeignResponse(@Param("subaccount_id") String subaccountId);

    @RequestLine("POST /accounts/subaccounts/{subaccount_id}/disable")
    SubaccountResponse disableSubaccount(@Param("subaccount_id") String subaccountId,
                                         @QueryMap DisableSubaccountQuery query);

    @RequestLine("POST /accounts/subaccounts/{subaccount_id}/disable")
    Response disableSubaccountFeignResponse(@Param("subaccount_id") String subaccountId,
                                             @QueryMap DisableSubaccountQuery query);

    @RequestLine("POST /accounts/subaccounts/{subaccount_id}/enable")
    SubaccountResponse enableSubaccount(@Param("subaccount_id") String subaccountId);

    @RequestLine("POST /accounts/subaccounts/{subaccount_id}/enable")
    Response enableSubaccountFeignResponse(@Param("subaccount_id") String subaccountId);

    @RequestLine("GET /accounts/subaccounts/{subaccount_id}/limit/custom/monthly")
    CustomMessageLimitResponse getCustomSendingLimit(@Param("subaccount_id") String subaccountId);

    @RequestLine("GET /accounts/subaccounts/{subaccount_id}/limit/custom/monthly")
    Response getCustomSendingLimitFeignResponse(@Param("subaccount_id") String subaccountId);

    @RequestLine("PUT /accounts/subaccounts/{subaccount_id}/limit/custom/monthly?limit={limit}")
    CustomMessageLimitSuccessResponse setCustomSendingLimit(@Param("subaccount_id") String subaccountId,
                                                             @Param("limit") BigDecimal limit);

    @RequestLine("PUT /accounts/subaccounts/{subaccount_id}/limit/custom/monthly?limit={limit}")
    Response setCustomSendingLimitFeignResponse(@Param("subaccount_id") String subaccountId,
                                                 @Param("limit") BigDecimal limit);

    @RequestLine("DELETE /accounts/subaccounts/{subaccount_id}/limit/custom/monthly")
    CustomMessageLimitSuccessResponse deleteCustomSendingLimit(@Param("subaccount_id") String subaccountId);

    @RequestLine("DELETE /accounts/subaccounts/{subaccount_id}/limit/custom/monthly")
    Response deleteCustomSendingLimitFeignResponse(@Param("subaccount_id") String subaccountId);

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @RequestLine("PUT /accounts/subaccounts/{subaccount_id}/features")
    SubaccountFeaturesResponse updateSubaccountFeatures(@Param("subaccount_id") String subaccountId,
                                                        UpdateSubaccountFeaturesRequest request);

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @RequestLine("PUT /accounts/subaccounts/{subaccount_id}/features")
    Response updateSubaccountFeaturesFeignResponse(@Param("subaccount_id") String subaccountId,
                                                    UpdateSubaccountFeaturesRequest request);

}
