package com.mailgun.model.tags;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Single tag entry returned in the analytics tags list response.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/tags-new/post-v1-analytics-tags.md">Post query to list account tags</a>
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnalyticsTagItem {

    @JsonProperty("account_id")
    String accountId;

    @JsonProperty("parent_account_id")
    String parentAccountId;

    String tag;

    String description;

    /**
     * ISO 8601 / RFC 2822 timestamp of when the tag was first seen.
     */
    @JsonProperty("first_seen")
    String firstSeen;

    /**
     * ISO 8601 / RFC 2822 timestamp of when the tag was last seen.
     */
    @JsonProperty("last_seen")
    String lastSeen;

    /**
     * Tag-level metrics. Populated only when {@code include_metrics=true} in the request.
     */
    AnalyticsTagMetrics metrics;

    @JsonProperty("account_name")
    String accountName;

}
