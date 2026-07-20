package com.mailgun.api.v5;

import com.mailgun.api.MailgunApi;
import com.mailgun.enums.ApiVersion;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.accountmanagement.AccountOperationSuccessResponse;
import com.mailgun.model.accountmanagement.HttpSigningKeyResponse;
import com.mailgun.model.accountmanagement.SandboxRecipientResponse;
import com.mailgun.model.accountmanagement.SandboxRecipientsResponse;
import com.mailgun.model.accountmanagement.UpdateAccountFeaturesRequest;
import com.mailgun.model.accountmanagement.UpdateAccountSettingsQuery;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import feign.Response;

/**
 * Account Management API.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/account-management">Account Management</a>
 */
@Headers("Accept: application/json")
public interface MailgunAccountManagementApi extends MailgunApi {

    static ApiVersion getApiVersion() {
        return ApiVersion.V_5;
    }

    @RequestLine("PUT /accounts")
    ResponseWithMessage updateAccountSettings(@QueryMap UpdateAccountSettingsQuery query);

    @RequestLine("PUT /accounts")
    Response updateAccountSettingsFeignResponse(@QueryMap UpdateAccountSettingsQuery query);

    @RequestLine("GET /accounts/http_signing_key")
    HttpSigningKeyResponse getHttpSigningKey();

    @RequestLine("GET /accounts/http_signing_key")
    Response getHttpSigningKeyFeignResponse();

    @RequestLine("POST /accounts/http_signing_key")
    HttpSigningKeyResponse regenerateHttpSigningKey();

    @RequestLine("POST /accounts/http_signing_key")
    Response regenerateHttpSigningKeyFeignResponse();

    @RequestLine("GET /sandbox/auth_recipients")
    SandboxRecipientsResponse getSandboxAuthorizedRecipients();

    @RequestLine("GET /sandbox/auth_recipients")
    Response getSandboxAuthorizedRecipientsFeignResponse();

    @RequestLine("POST /sandbox/auth_recipients?email={email}")
    SandboxRecipientResponse addSandboxAuthorizedRecipient(@Param("email") String email);

    @RequestLine("POST /sandbox/auth_recipients?email={email}")
    Response addSandboxAuthorizedRecipientFeignResponse(@Param("email") String email);

    @RequestLine("DELETE /sandbox/auth_recipients/{email}")
    ResponseWithMessage deleteSandboxAuthorizedRecipient(@Param("email") String email);

    @RequestLine("DELETE /sandbox/auth_recipients/{email}")
    Response deleteSandboxAuthorizedRecipientFeignResponse(@Param("email") String email);

    @RequestLine("POST /accounts/resend_activation_email")
    AccountOperationSuccessResponse resendActivationEmail();

    @RequestLine("POST /accounts/resend_activation_email")
    Response resendActivationEmailFeignResponse();

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @RequestLine("PUT /accounts/features")
    AccountOperationSuccessResponse updateAccountFeatures(UpdateAccountFeaturesRequest request);

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @RequestLine("PUT /accounts/features")
    Response updateAccountFeaturesFeignResponse(UpdateAccountFeaturesRequest request);

}
