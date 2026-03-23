package com.mailgun.model.tags;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

/**
 * Response for {@code GET /v3/{domain}/tag/stats/aggregates}.
 * Contains combined aggregate counts by provider, country, and device for a tag.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/tags/get-v3--domain--tag-stats-aggregates.md">Get aggregate stat types by tag</a>
 * @deprecated Use the new Tags API instead.
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class TagAggregatesResponse {

    /**
     * Name of the tag.
     */
    String tag;

    /**
     * Aggregate counts by email service provider.
     */
    Map<String, Map<String, Integer>> provider;

    /**
     * Aggregate counts by country.
     */
    Map<String, Map<String, Integer>> country;

    /**
     * Aggregate counts by device type.
     */
    Devices device;

}
