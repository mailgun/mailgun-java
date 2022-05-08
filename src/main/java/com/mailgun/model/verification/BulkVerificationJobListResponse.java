package com.mailgun.model.verification;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mailgun.model.Paging;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Bulk verification job list Response.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-email-validation.html#bulk-verification">Bulk Verification</a>
 */
@Value
@Jacksonized
@Builder
public class BulkVerificationJobListResponse {

    /**
     * <p>
     * Verification jobs {@link BulkVerificationJobStatusResponse}.
     * </p>
     */
    @JsonProperty("jobs")
    List<BulkVerificationJobStatusResponse> jobs;

    /**
     * <p>
     * The total number of verification jobs.
     * </p>
     */
    @JsonProperty("total")
    Integer total;

    /**
     * <p>
     * Provides pagination urls.
     * </p>
     * {@link Paging}
     */
    @JsonProperty("paging")
    Paging paging;

}
