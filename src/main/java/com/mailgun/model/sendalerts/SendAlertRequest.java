package com.mailgun.model.sendalerts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Request body for creating or replacing a send alert (POST or full PUT).
 * <p>
 * PUT replaces the entire resource; clients should GET first, then send the full configuration.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/send-alerts/post-v1-thresholds-alerts-send.md">Create a send alert</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/send-alerts/put-v1-thresholds-alerts-send--name-.md">Update a send alert</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SendAlertRequest {

    /**
     * User-friendly name for the alert.
     */
    String name;

    /**
     * Metric to monitor (API string; see {@link com.mailgun.enums.SendAlertMetric}).
     */
    String metric;

    /**
     * Threshold comparison (API string; see {@link com.mailgun.enums.SendAlertThresholdComparator}).
     */
    String comparator;

    /**
     * Threshold limit (format defined by the metric).
     */
    String limit;

    /**
     * Dimension for the metric (API string; see {@link com.mailgun.enums.SendAlertDimension}).
     */
    String dimension;

    /**
     * Channels to notify (API strings; see {@link com.mailgun.enums.SendAlertChannel}).
     */
    @JsonProperty("alert_channels")
    List<String> alertChannels;

    List<SendAlertFilter> filters;

    /**
     * Aggregation window, e.g. {@code 1h} or {@code 1d}.
     */
    String period;

    String description;

}
