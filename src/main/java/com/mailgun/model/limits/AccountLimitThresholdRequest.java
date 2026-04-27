package com.mailgun.model.limits;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mailgun.model.sendalerts.SendAlertFilter;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Request body for {@code POST /v1/thresholds/limits} and full replacement {@code PUT /v1/thresholds/limits/{name}}.
 * <p>
 * PUT replaces the entire resource; use GET first if you only need to change part of the configuration.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/limits/post-v1-thresholds-limits.md">Create a limit threshold</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/limits/put-v1-thresholds-limits--name-.md">Update a limit threshold</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountLimitThresholdRequest {

    String name;

    /**
     * Metric (API string; see {@link com.mailgun.enums.LimitMetric}).
     */
    String metric;

    /**
     * Threshold comparison (API string; see {@link com.mailgun.enums.SendAlertThresholdComparator}).
     */
    String comparator;

    String limit;

    /**
     * Dimension (API string; see {@link com.mailgun.enums.SendAlertDimension}).
     */
    String dimension;

    List<SendAlertFilter> filters;

    String period;

    String description;

}
