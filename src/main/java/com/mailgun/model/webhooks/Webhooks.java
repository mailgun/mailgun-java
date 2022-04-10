package com.mailgun.model.webhooks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Webhooks.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-webhooks.html#webhooks">Webhooks</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Webhooks {

    /**
     * <p>
     * Tracking Clicks.
     * </p>
     *
     * @see <a href="https://documentation.mailgun.com/en/latest/user_manual.html#um-tracking-clicks">Tracking Clicks</a>
     */
    Webhook clicked;

    /**
     * <p>
     * Tracking Spam Complaints.
     * </p>
     *
     * @see <a href="https://documentation.mailgun.com/en/latest/user_manual.html#um-tracking-spam-complaints">Tracking Spam Complaints</a>
     */
    Webhook complained;

    /**
     * <p>
     * Tracking Deliveries.
     * </p>
     *
     * @see <a href="https://documentation.mailgun.com/en/latest/user_manual.html#um-tracking-deliveries">Tracking Deliveries</a>
     */
    Webhook delivered;

    /**
     * <p>
     * Tracking Opens.
     * </p>
     *
     * @see <a href="https://documentation.mailgun.com/en/latest/user_manual.html#um-tracking-opens">Tracking Opens</a>
     */
    Webhook opened;

    /**
     * <p>
     * Tracking Permanent Failures.
     * </p>
     *
     * @see <a href="https://documentation.mailgun.com/en/latest/user_manual.html#um-tracking-failures">Tracking Failures</a>
     */
    @JsonProperty("permanent_fail")
    Webhook permanentFail;

    /**
     * <p>
     * Tracking Temporary Failures.
     * </p>
     *
     * @see <a href="https://documentation.mailgun.com/en/latest/user_manual.html#um-tracking-failures">Tracking Failures</a>
     */
    @JsonProperty("temporary_fail")
    Webhook temporaryFail;

    /**
     * <p>
     * Tracking Unsubscribes.
     * </p>
     *
     * @see <a href="https://documentation.mailgun.com/en/latest/user_manual.html#um-tracking-unsubscribes">Tracking Unsubscribes</a>
     */
    Webhook unsubscribed;

}
