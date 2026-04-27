package com.mailgun.model.alerts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Response for {@code GET /v1/alerts/events}: selectable alert event identifiers.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/alerts/get-v1-alerts-events.md">List events</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlertAvailableEventsResponse {

    /**
     * Event type strings you may pass as {@code event_type} when creating alert settings.
     */
    List<String> events;

}
