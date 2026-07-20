package com.mailgun.model.accountmanagement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Account webhook signing key response.
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class HttpSigningKeyResponse {

    String message;

    @JsonProperty("http_signing_key")
    String httpSigningKey;

}
