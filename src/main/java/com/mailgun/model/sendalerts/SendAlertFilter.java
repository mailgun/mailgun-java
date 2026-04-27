package com.mailgun.model.sendalerts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Dimension filter used by send alerts and account limit thresholds (same JSON shape).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/send-alerts/post-v1-thresholds-alerts-send.md">Create a send alert</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/limits/post-v1-thresholds-limits.md">Create a limit threshold</a>
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
