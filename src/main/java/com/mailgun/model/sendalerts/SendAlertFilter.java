package com.mailgun.model.sendalerts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Filter applied to a send alert.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/send-alerts/post-v1-thresholds-alerts-send.md">Create a send alert</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendAlertFilter {

    /**
     * Dimension to filter by (API string; see {@link com.mailgun.enums.SendAlertDimension}).
     */
    String dimension;

    /**
     * Comparison operator (API string; see {@link com.mailgun.enums.SendAlertFilterComparator}).
     */
    String comparator;

    /**
     * Values the filter applies to.
     */
    List<String> values;

}
