package com.mailgun.model.tags;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Response body for the analytics tags limits endpoint (GET /v1/analytics/tags/limits).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/tags-new/get-v1-analytics-tags-limits.md">Get account tag limit information</a>
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnalyticsTagLimitsResponse {

    /**
     * Maximum number of unique tags allowed for the account.
     */
    Integer limit;

    /**
     * Current number of unique tags in use.
     */
    Integer count;

    /**
     * Whether the tag limit has been reached.
     */
    @JsonProperty("limit_reached")
    Boolean limitReached;

}
