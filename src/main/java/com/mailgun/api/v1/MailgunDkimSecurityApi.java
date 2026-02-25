package com.mailgun.api.v1;

import com.mailgun.api.MailgunApi;
import com.mailgun.enums.ApiVersion;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.dkimsecurity.DkimRotationUpdateRequest;
import com.mailgun.model.dkimsecurity.DkimRotationUpdateResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;

/**
 * DKIM Security API (v1): Automatic Sender Security DKIM key management. Enable the feature via the "Update a domain" API.
 * <p>
 * Update rotation settings or trigger an immediate key rotation for a domain.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dkim-security/put-v1-dkim-management-domains--name--rotation">Update Automatic Sender Security DKIM key rotation</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dkim-security/post-v1-dkim-management-domains--name--rotate">Rotate Automatic Sender Security DKIM key</a>
 */
@Headers("Accept: application/json")
public interface MailgunDkimSecurityApi extends MailgunApi {

    static ApiVersion getApiVersion() {
        return ApiVersion.V_1;
    }

    /**
     * Update Automatic Sender Security DKIM key rotation for a domain. Minimum allowed rotation interval is 5 days.
     *
     * @param name    domain name (path parameter)
     * @param request rotation_enabled (required), optional rotation_interval (e.g. "5d")
     * @return {@link DkimRotationUpdateResponse} with domain details and records
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dkim-security/put-v1-dkim-management-domains--name--rotation">Update Automatic Sender Security DKIM key rotation</a>
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /dkim_management/domains/{name}/rotation")
    DkimRotationUpdateResponse updateRotation(@Param("name") String name, DkimRotationUpdateRequest request);

    /**
     * Update rotation (raw response).
     *
     * @param name    domain name
     * @param request rotation update request
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /dkim_management/domains/{name}/rotation")
    Response updateRotationFeignResponse(@Param("name") String name, DkimRotationUpdateRequest request);

    /**
     * Immediately rotate the Automatic Sender Security DKIM key for a domain. Triggers rotation even if auto-rotation is disabled.
     *
     * @param name domain name (path parameter)
     * @return {@link ResponseWithMessage} with success message
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dkim-security/post-v1-dkim-management-domains--name--rotate">Rotate Automatic Sender Security DKIM key</a>
     */
    @RequestLine("POST /dkim_management/domains/{name}/rotate")
    ResponseWithMessage rotate(@Param("name") String name);

    /**
     * Rotate DKIM key (raw response).
     *
     * @param name domain name
     * @return {@link Response}
     */
    @RequestLine("POST /dkim_management/domains/{name}/rotate")
    Response rotateFeignResponse(@Param("name") String name);
}
