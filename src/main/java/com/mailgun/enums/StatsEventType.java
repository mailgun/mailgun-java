package com.mailgun.enums;


import com.fasterxml.jackson.annotation.JsonValue;

/**
 * <p>
 * Mailgun tracks all of the events that occur throughout the system.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-stats.html#event-types">Event Types</a>
 */
public enum StatsEventType {

    /**
     * <p>
     * Mailgun accepted the request to send/forward the email and the message has been placed in queue.
     * </p>
     */
    ACCEPTED("accepted"),

    /**
     * <p>
     * Mailgun sent the email and it was accepted by the recipient email server.
     * </p>
     */
    DELIVERED("delivered"),

    /**
     * <p>
     * Mailgun could not deliver the email to the recipient email server.
     * </p>
     */
    FAILED("failed"),

    /**
     * <p>
     * The email recipient opened the email and enabled image viewing. Open tracking must be enabled in the Mailgun control panel,
     * and the CNAME record must be pointing to mailgun.org.
     * </p>
     */
    OPENED("opened"),

    /**
     * <p>
     * The email recipient clicked on a link in the email. Click tracking must be enabled in the Mailgun control panel,
     * and the CNAME record must be pointing to mailgun.org.
     * </p>
     */
    CLICKED("clicked"),

    /**
     * <p>
     * The email recipient clicked on the unsubscribe link. Unsubscribe tracking must be enabled in the Mailgun control panel.
     * </p>
     */
    UNSUBSCRIBED("unsubscribed"),

    /**
     * <p>
     * The email recipient clicked on the spam complaint button within their email client.
     * Feedback loops enable the notification to be received by Mailgun.
     * </p>
     */
    COMPLAINED("complained"),

    /**
     * <p>
     * Mailgun has stored an incoming message
     * </p>
     */
    STORED("stored");

    private final String value;

    StatsEventType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

}
