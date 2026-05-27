package com.mailgun.model.ips;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Response for {@code GET /v3/ips/request/new} (deprecated; backwards compatibility only).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/ips/get-v3-ips-request-new">Return available IPs per billing plan</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestNewIpAvailabilityResponse {

    String message;

    /**
     * Dedicated IP availability (shape varies; additional fields ignored).
     */
    Integer dedicated;

    /**
     * Deprecated; do not use in new code.
     */
    @Deprecated
    @JsonProperty("shared")
    Integer shared;

}
