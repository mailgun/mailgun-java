package com.mailgun.model.stats;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

/**
 * Response for {@code GET /v3/{domain}/aggregates/providers}.
 * Aggregate counts by email service provider.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/stats/get-v3--domain--aggregates-providers.md">Aggregate counts by ESP</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatsAggregateProvidersResponse {

    /**
     * Map of provider name to event-type counts.
     */
    Map<String, Object> providers;

}
