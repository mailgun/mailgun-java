package com.mailgun.model.keys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.ToString;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Response from regenerating the account public API key.
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PublicApiKeyResponse {

    @ToString.Exclude
    String key;

    String message;
}
