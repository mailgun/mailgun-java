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
 * Bulk verification preview status Response.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-email-validation.html#bulk-verification">Bulk Verification</a>
 */
@Value
@Jacksonized
@Builder
public class BulkVerificationPreviewStatusResponse {

    /**
     * <p>
     * list_id name given when the list was initially created.
     * </p>
     */
    @JsonProperty("id")
    String id;

    /**
     * <p>
     * Number of total items in the list to be previewed.
     * </p>
     */
    @JsonProperty("quantity")
    Integer quantity;

    /**
     * <p>
     * Current state of the list verification request.
     * </p>
     */
    @JsonProperty("status")
    String status;

    /**
     * <p>
     * A boolean to represent if the list is valid.
     * </p>
     */
    @JsonProperty("valid")
    Boolean valid;

    @JsonProperty("reason")
    String reason;

    /**
     * <p>
     * Summary of the verifications in the list provided.
     * </p>
     */
    @JsonProperty("summary") BulkVerificationStatusSummary summary;

    /**
     * <p>
     * Date/Time that the request was initiated.
     * </p>
     * {@link ZonedDateTime}
     */
    @JsonFormat(pattern = RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NAME)
    @JsonProperty("created_at")
    ZonedDateTime createdAt;

}
