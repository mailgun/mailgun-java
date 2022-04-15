package com.mailgun.api.v4;

import com.mailgun.api.MailgunApi;
import com.mailgun.enums.ApiVersion;
import com.mailgun.model.verification.AddressValidationResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;

/**
 * <p>
 * This API endpoint is an email address verification service.
 * </p>
 * <p>
 * We will verify the given address based on:
 * </p>
 * <pre>
 * Mailbox detection
 * Syntax checks (RFC defined grammar)
 * DNS validation
 * Spell checks
 * Email Service Provider (ESP) specific local-part grammar (if available).
 * </pre>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-email-validation.html">Email Validation/Verification</a>
 */
public interface MailgunEmailVerificationApi extends MailgunApi {

    @Override
    default ApiVersion getApiVersion() {
        return ApiVersion.V_4;
    }

    /**
     * <p>
     * Given an arbitrary address, validates address based off defined checks.
     * </p>
     *
     * @param address An email address to validate. (Maximum: 512 characters)
     * @return {@link AddressValidationResponse}
     */
    @Headers("Accept: application/json")
    @RequestLine("GET /address/validate?address={address}")
    AddressValidationResponse validateAddress(@Param("address") String address);

    /**
     * <p>
     * Given an arbitrary address, validates address based off defined checks.
     * </p>
     *
     * @param address An email address to validate. (Maximum: 512 characters)
     * @return {@link Response}
     */
    @Headers("Accept: application/json")
    @RequestLine("GET /address/validate?address={address}")
    Response validateAddressFeignResponse(@Param("address") String address);

    /**
     * <p>
     * Given an arbitrary address, validates address based off defined checks.
     * </p>
     *
     * @param address An email address to validate. (Maximum: 512 characters)
     * @return {@link AddressValidationResponse}
     */
    @Headers({"Content-Type: multipart/form-data", "Accept: application/json"})
    @RequestLine("POST /address/validate")
    AddressValidationResponse validateAddressPostRequest(@Param("address") String address);

    /**
     * <p>
     * Given an arbitrary address, validates address based off defined checks.
     * </p>
     *
     * @param address An email address to validate. (Maximum: 512 characters)
     * @return {@link Response}
     */
    @Headers({"Content-Type: multipart/form-data", "Accept: application/json"})
    @RequestLine("POST /address/validate")
    Response validateAddressPostRequestFeignResponse(@Param("address") String address);

    /**
     * <p>
     * Given an arbitrary address, validates address based off defined checks.
     * </p>
     *
     * @param address        An email address to validate. (Maximum: 512 characters)
     * @param providerLookup Warning: For advanced users only.
     *                       The provider_lookup query parameter provides users with the control to allow or prevent Mailgun from reaching out to the mailbox provider.
     *                       If set to <code>true</code> - A provider lookup will be performed if Mailgun’s internal analysis is insufficient.
     * @return {@link AddressValidationResponse}
     */
    @Headers("Accept: application/json")
    @RequestLine("GET /address/validate?address={address}&provider_lookup={providerLookup}")
    AddressValidationResponse validateAddress(@Param("address") String address, @Param("providerLookup") Boolean providerLookup);

    /**
     * <p>
     * Given an arbitrary address, validates address based off defined checks.
     * </p>
     *
     * @param address        An email address to validate. (Maximum: 512 characters)
     * @param providerLookup Warning: For advanced users only.
     *                       The provider_lookup query parameter provides users with the control to allow or prevent Mailgun from reaching out to the mailbox provider.
     *                       If set to <code>true</code> - A provider lookup will be performed if Mailgun’s internal analysis is insufficient.
     * @return {@link Response}
     */
    @Headers("Accept: application/json")
    @RequestLine("GET /address/validate?address={address}&provider_lookup={providerLookup}")
    Response validateAddressFeignResponse(@Param("address") String address, @Param("providerLookup") Boolean providerLookup);

    /**
     * <p>
     * Given an arbitrary address, validates address based off defined checks.
     * </p>
     *
     * @param address        An email address to validate. (Maximum: 512 characters)
     * @param providerLookup Warning: For advanced users only
     *                       The provider_lookup query parameter provides users with the control to allow or prevent Mailgun from reaching out to the mailbox provider.
     *                       If set to <code>true</code> - A provider lookup will be performed if Mailgun’s internal analysis is insufficient.
     * @return {@link AddressValidationResponse}
     */
    @Headers({"Content-Type: multipart/form-data", "Accept: application/json"})
    @RequestLine("POST /address/validate?provider_lookup={providerLookup}")
    AddressValidationResponse validateAddressPostRequest(@Param("address") String address, @Param("providerLookup") Boolean providerLookup);

    /**
     * <p>
     * Given an arbitrary address, validates address based off defined checks.
     * </p>
     *
     * @param address        An email address to validate. (Maximum: 512 characters)
     * @param providerLookup Warning: For advanced users only
     *                       The provider_lookup query parameter provides users with the control to allow or prevent Mailgun from reaching out to the mailbox provider.
     *                       If set to <code>true</code> - A provider lookup will be performed if Mailgun’s internal analysis is insufficient.
     * @return {@link Response}
     */
    @Headers({"Content-Type: multipart/form-data", "Accept: application/json"})
    @RequestLine("POST /address/validate?provider_lookup={providerLookup}")
    Response validateAddressPostFeignResponse(@Param("address") String address, @Param("providerLookup") Boolean providerLookup);

}
