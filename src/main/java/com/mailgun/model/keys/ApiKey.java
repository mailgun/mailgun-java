package com.mailgun.model.keys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.ToString;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Mailgun API key metadata.
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiKey {

    String id;

    String description;

    String kind;

    String role;

    @JsonProperty("created_at")
    String createdAt;

    @JsonProperty("updated_at")
    String updatedAt;

    @JsonProperty("expires_at")
    String expiresAt;

    @JsonProperty("disabled_reason")
    String disabledReason;

    @JsonProperty("is_disabled")
    Boolean isDisabled;

    @JsonProperty("domain_name")
    String domainName;

    String requestor;

    @JsonProperty("user_name")
    String userName;

    /** Returned only once, when the key is created. */
    @ToString.Exclude
    String secret;
}
