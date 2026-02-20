package com.mailgun.model.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mailgun.enums.Severity;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Event.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-events.html">Events API</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventItem {

    /**
     * <p>
     * Event id.
     * </p>
     */
    String id;

    /**
     * <p>
     * Event Type.
     * </p>
     */
    String event;

    /**
     * <p>
     * The time when the event was generated in the system.
     * </p>
     */
    ZonedDateTime timestamp;

    /**
     * <p>
     * Event log level.
     * </p>
     */
    @JsonProperty("log-level")
    String logLevel;

    /**
     * <p>
     * {@link EventMember}
     * </p>
     */
    EventMember member;

    /**
     * <p>
     * M
     * Event method.
     * </p>
     * E.g.:<code>HTTP</code>, <code>smtp</code>.
     */
    String method;

    /**
     * <p>
     * {@link Severity}
     * </p>
     */
    Severity severity;

    /**
     * <p>
     * Event reason.
     * </p>
     */
    String reason;

    /**
     * <p>
     * List of {@link EventRoute}.
     * </p>
     */
    List<EventRoute> routes;

    /**
     * <p>
     * {@link EventEnvelope}
     * </p>
     */
    EventEnvelope envelope;

    /**
     * <p>
     * {@link EventFlags}
     * </p>
     */
    EventFlags flags;

    /**
     * <p>
     * {@link EventDeliveryStatus}
     * </p>
     */
    @JsonProperty("delivery-status")
    EventDeliveryStatus deliveryStatus;

    /**
     * <p>
     * {@link RejectedEventInfo}
     * </p>
     */
    RejectedEventInfo reject;

    /**
     * <p>
     * {@link EventMessage}
     * </p>
     */
    EventMessage message;

    /**
     * <p>
     * {@link Storage}
     * </p>
     */
    Storage storage;

    /**
     * <p>
     * An email address of a particular recipient.
     * </p>
     */
    String recipient;

    /**
     * <p>
     * Recipient domain.
     * </p>
     */
    @JsonProperty("recipient-domain")
    String recipientDomain;

    /**
     * <p>
     * List of campaigns.
     * </p>
     */
    List<String> campaigns;

    /**
     * <p>
     * {@link EventGeolocation}
     * </p>
     */
    EventGeolocation geolocation;

    /**
     * <p>
     * List of tags.
     * </p>
     */
    List<String> tags;

    /**
     * <p>
     * Map of user variables.
     * </p>
     */
    @JsonProperty("user-variables")
    Map<String, Object> userVariables;

    /**
     * <p>
     * Event url.
     * </p>
     */
    String url;

    /**
     * <p>
     * Event ip.
     * </p>
     */
    String ip;

    /**
     * <p>
     * {@link EventClientInfo}
     * </p>
     */
    @JsonProperty("client-info")
    EventClientInfo clientInfo;

    /**
     * <p>
     * Event task id.
     * </p>
     */
    @JsonProperty("task-id")
    String taskId;

    /**
     * <p>
     * Event format.
     * </p>
     */
    String format;

    /**
     * <p>
     * {@link EventMailingList}
     * </p>
     */
    @JsonProperty("mailing-list")
    EventMailingList mailingList;

    @JsonProperty("failed-count")
    Integer failedCount;

    @JsonProperty("upserted-count")
    Integer upsertedCount;

    @JsonProperty("is-upsert")
    Boolean isUpsert;

}
