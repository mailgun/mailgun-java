package com.mailgun.model.mailing.lists;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;

import static com.mailgun.util.Constants.RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NAME;


/**
 * <p>
 * Mailing list verification status response.
 * </p>
 */
@Value
@Jacksonized
@Builder
public class MailingListVerificationStatusResponse {

    /**
     * <p>
     * List name given when the list was initially created.
     * An email address, the ID for the mailing list.
     * </p>
     */
    String id;

    /**
     * <p>
     * Current state of the list verification request.
     * </p>
     */
    String status;

    /**
     * <p>
     * Number of total items in the list to be verified.
     * </p>
     */
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
     * Date/Time that the request was initiated.
     * </p>
     * {@link ZonedDateTime}
     */
    @JsonFormat(pattern = RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NAME, locale = "en")
    @JsonProperty("created_at")
    ZonedDateTime createdAt;

}
