package com.mailgun.model.stats;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

/**
 * Response for {@code GET /v3/{domain}/aggregates/countries}.
 * Aggregate counts by country (USA, RUS, etc.).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/stats/get-v3--domain--aggregates-countries.md">Aggregate counts by country</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatsAggregateCountriesResponse {

    /**
     * Map of country code to event-type counts.
     */
    Map<String, Object> countries;

}
