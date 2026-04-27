package com.mailgun.model.limits;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Response for {@code GET /v1/thresholds/limits}.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/limits/get-v1-thresholds-limits.md">List limit thresholds</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountLimitsListResult {

    List<AccountLimitThreshold> items;

    Integer total;

}
