package com.mailgun.api.v1;

import com.mailgun.api.MailgunApi;
import com.mailgun.enums.ApiVersion;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.keys.ApiKeyCreateRequest;
import com.mailgun.model.keys.ApiKeyCreateResponse;
import com.mailgun.model.keys.ApiKeysListQuery;
import com.mailgun.model.keys.ApiKeysListResponse;
import com.mailgun.model.keys.PublicApiKeyResponse;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import feign.Response;

/**
 * Keys API (v1): list, create, and delete Mailgun API keys, and regenerate the public API key.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/keys">Keys API</a>
 */
@Headers("Accept: application/json")
public interface MailgunKeysApi extends MailgunApi {

    static ApiVersion getApiVersion() {
        return ApiVersion.V_1;
    }

    @RequestLine("GET /keys")
    ApiKeysListResponse listApiKeys();

    @RequestLine("GET /keys")
    ApiKeysListResponse listApiKeys(@QueryMap ApiKeysListQuery query);

    @RequestLine("GET /keys")
    Response listApiKeysFeignResponse();

    @RequestLine("GET /keys")
    Response listApiKeysFeignResponse(@QueryMap ApiKeysListQuery query);

    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /keys")
    ApiKeyCreateResponse createApiKey(ApiKeyCreateRequest request);

    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /keys")
    Response createApiKeyFeignResponse(ApiKeyCreateRequest request);

    @RequestLine("DELETE /keys/{key_id}")
    ResponseWithMessage deleteApiKey(@Param("key_id") String keyId);

    @RequestLine("DELETE /keys/{key_id}")
    Response deleteApiKeyFeignResponse(@Param("key_id") String keyId);

    @RequestLine("POST /keys/public")
    PublicApiKeyResponse regeneratePublicApiKey();

    @RequestLine("POST /keys/public")
    Response regeneratePublicApiKeyFeignResponse();
}
