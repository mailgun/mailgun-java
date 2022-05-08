package com.mailgun.model.verification;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Summary of the verifications in the list provided.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-email-validation.html#bulk-verification">Bulk Verification</a>
 */
@Value
@Jacksonized
@Builder
public class BulkVerificationJobStatusSummary {

    /**
     * <p>
     * Nested results count.
     * </p>
     */
    @JsonProperty("result")
    BulkVerificationJobStatusSummaryResult result;

    /**
     * <p>
     * Nested risk assessment count.
     * </p>
     */
    @JsonProperty("risk")
    BulkVerificationJobStatusSummaryRisk risk;

}
