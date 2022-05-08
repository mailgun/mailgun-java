package com.mailgun.model.verification;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Bulk verification status summary result.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-email-validation.html#bulk-verification">Bulk Verification</a>
 */
@Value
@Jacksonized
@Builder
public class BulkVerificationStatusSummaryResult {

    /**
     * <p>
     * The total number of domain associated with result is considered a catch_all domain.
     * </p>
     */
    @JsonProperty("catch_all")
    Integer catchAll;

    /**
     * <p>
     * The collection of verification jobs requested for.
     * </p>
     */
    @JsonProperty("deliverable")
    Integer deliverable;

    /**
     * <p>
     * A collection of pagination links for traversing the verification jobs.
     * </p>
     */
    @JsonProperty("do_not_send")
    Integer doNotSend;

    /**
     * <p>
     * The total number of verification jobs.
     * </p>
     */
    @JsonProperty("undeliverable")
    Integer undeliverable;

    @JsonProperty("unknown")
    Integer unknown;

}
