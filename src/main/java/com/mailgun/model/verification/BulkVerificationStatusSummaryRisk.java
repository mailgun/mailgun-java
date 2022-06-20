package com.mailgun.model.verification;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Bulk verification status summary risk.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-email-validation.html#bulk-verification">Bulk Verification</a>
 */
@Value
@Jacksonized
@Builder
public class BulkVerificationStatusSummaryRisk {

    @JsonProperty("high")
    Integer high;

    @JsonProperty("low")
    Integer low;

    @JsonProperty("medium")
    Integer medium;

    @JsonProperty("unknown")
    Integer unknown;

}
