package com.mailgun.model.verification;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import static com.mailgun.util.Constants.RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NAME;

/**
 * <p>
 * Bulk verification job status Response.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-email-validation.html#bulk-verification">Bulk Verification</a>
 */
@Value
@Jacksonized
@Builder
public class BulkVerificationJobStatusResponse {

    /**
     * <p>
     * list_id name given when the list was initially created.
     * </p>
     */
    @JsonProperty("id")
    String id;

    /**
     * <p>
     * Number of total items in the list to be verified.
     * </p>
     */
    @JsonProperty("quantity")
    Integer quantity;

    /**
     * <p>
     * De-duplicated total of verified email addresses.
     * </p>
     */
    @JsonProperty("records_processed")
    Integer recordsProcessed;

    /**
     * <p>
     * Job status.
     * </p>
     */
    @JsonProperty("status")
    String status;

    /**
     * <p>
     * Summary of the verifications in the list provided.
     * </p>
     */
    @JsonProperty("summary")
    BulkVerificationStatusSummary summary;

    /**
     * <p>
     * Date/Time that the request was initiated.
     * </p>
     * {@link ZonedDateTime}
     */
    @JsonFormat(pattern = RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NAME, locale = "en")
    @JsonProperty("created_at")
    ZonedDateTime createdAt;

    /**
     * <p>
     * <code>csv</code> and <code>json</code> representation of the download link for the results of the bulk verifications.
     * </p>
     * {@link BulkVerificationDownloadUrl}
     */
    @JsonProperty("download_url")
    BulkVerificationDownloadUrl downloadUrl;

}
