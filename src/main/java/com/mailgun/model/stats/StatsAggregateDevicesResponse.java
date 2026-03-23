package com.mailgun.model.stats;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

/**
 * Response for {@code GET /v3/{domain}/aggregates/devices}.
 * Aggregate counts on devices that triggered events ('tablet', 'phone', 'pc', etc.).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/stats/get-v3--domain--aggregates-devices.md">Aggregate counts by devices triggering events</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatsAggregateDevicesResponse {

    /**
     * Map of device type to event-type counts.
     */
    Map<String, Object> devices;

}
