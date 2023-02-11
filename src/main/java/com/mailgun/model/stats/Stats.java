package com.mailgun.model.stats;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;

import static com.mailgun.util.Constants.ENGLISH;
import static com.mailgun.util.Constants.RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NAME;

/**
 * <p>
 * Mailgun tracks all of the events that occur throughout the system.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-stats.html#stats">Stats</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Stats {

    /**
     * <p>
     * The starting time.
     * </p>
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NAME, locale = ENGLISH)
    ZonedDateTime time;

    /**
     * <p>
     * Mailgun accepted the request to send/forward the email and the message has been placed in queue.
     * </p>
     * {@link Accepted}
     */
    Accepted accepted;

    /**
     * <p>
     * Mailgun sent the email and it was accepted by the recipient email server.
     * </p>
     * {@link Delivered}
     */
    Delivered delivered;

    /**
     * <p>
     * Mailgun could not deliver the email to the recipient email server.
     * </p>
     * {@link Failed}
     */
    Failed failed;

    /**
     * <p>
     * Mailgun has stored an incoming message
     * </p>
     * {@link StatsTotalValueObject}
     */
    StatsTotalValueObject stored;

    /**
     * <p>
     * The email recipient opened the email and enabled image viewing. Open tracking must be enabled in the Mailgun control panel,
     * and the CNAME record must be pointing to mailgun.org.
     * </p>
     * {@link StatsTotalValueObject}
     */
    StatsTotalValueObject opened;

    /**
     * <p>
     * The email recipient clicked on a link in the email. Click tracking must be enabled in the Mailgun control panel,
     * and the CNAME record must be pointing to mailgun.org.
     * </p>
     * {@link StatsTotalValueObject}
     */
    StatsTotalValueObject clicked;

    /**
     * <p>
     * The email recipient clicked on the unsubscribe link. Unsubscribe tracking must be enabled in the Mailgun control panel.
     * </p>
     * {@link StatsTotalValueObject}
     */
    StatsTotalValueObject unsubscribed;

    /**
     * <p>
     * The email recipient clicked on the spam complaint button within their email client.
     * Feedback loops enable the notification to be received by Mailgun.
     * </p>
     * {@link StatsTotalValueObject}
     */
    StatsTotalValueObject complained;

}
