package com.mailgun.model.logs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Log item for Mailgun Logs API.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/openapi-final/logs/post-v1-analytics-logs">Mailgun Logs API</a>
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogItem {
    /**
     * Unique identifier for the log event. Required.
     */
    String id;
    /**
     * Event type string (e.g., "delivered", "failed"). Required.
     */
    String event;
    /**
     * Timestamp of the event (RFC 3339 or RFC 2822 format). Required.
     */
    @JsonProperty("@timestamp")
    String timestamp;
    /**
     * Log level string.
     */
    @JsonProperty("log-level")
    String logLevel;
    /**
     * Account object. Contains information about the account associated with the event. Optional.
     */
    Map<String, Object> account;
    /**
     * List of campaign objects associated with the event. Optional.
     */
    List<Map<String, Object>> campaigns;
    /**
     * List of tags associated with the event. Optional.
     */
    List<String> tags;
    /**
     * Method used for the event (e.g., "smtp", "http"). Optional.
     */
    String method;
    /**
     * Originating IP address for the event. Optional.
     */
    @JsonProperty("originating-ip")
    String originatingIp;
    /**
     * API key ID used for the event. Optional.
     */
    @JsonProperty("api-key-id")
    String apiKeyId;
    /**
     * Delivery status object. Contains details about delivery status. Optional.
     */
    @JsonProperty("delivery-status")
    Map<String, Object> deliveryStatus;
    /**
     * Delivery optimizer string. Optional.
     */
    @JsonProperty("i-delivery-optimizer")
    String iDeliveryOptimizer;
    /**
     * Domain object. Contains information about the domain associated with the event. Required.
     */
    Map<String, Object> domain;
    /**
     * Recipient email address. Optional.
     */
    String recipient;
    /**
     * Recipient domain. Optional.
     */
    @JsonProperty("recipient-domain")
    String recipientDomain;
    /**
     * Recipient provider. Optional.
     */
    @JsonProperty("recipient-provider")
    String recipientProvider;
    /**
     * Envelope object. Contains SMTP envelope information. Optional.
     */
    Map<String, Object> envelope;
    /**
     * Storage object. Optional.
     */
    Map<String, Object> storage;
    /**
     * Template object. Optional.
     */
    Map<String, Object> template;
    /**
     * Message object. Contains message details. Optional.
     */
    Message message;
    /**
     * User variables. Optional.
     */
    @JsonProperty("user-variables")
    String userVariables;
    /**
     * Flags object. Contains event flags. Optional.
     */
    Flags flags;
    /**
     * Primary DKIM string. Optional.
     */
    @JsonProperty("primary-dkim")
    String primaryDkim;
    /**
     * IP string. Optional.
     */
    String ip;
    /**
     * Geolocation object. Optional.
     */
    GeoLocation geolocation;
    /**
     * Client info object. Optional.
     */
    @JsonProperty("client-info")
    ClientInfo clientInfo;
    /**
     * Severity string. Optional.
     */
    String severity;
    /**
     * Reason string. Optional.
     */
    String reason;
    /**
     * Routes object. Optional.
     */
    Routes routes;
    /**
     * Mailing list object. Optional.
     */
    @JsonProperty("mailing-list")
    MailingList mailingList;
    /**
     * URL string. Optional.
     */
    String url;

    // --- Nested Classes ---

    /**
     * Message object for Mailgun Logs API.
     */
    @Value
    @Builder
    @Jacksonized
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Message {
        /**
         * Message headers.
         */
        MessageHeaders headers;
        /**
         * List of attachments.
         */
        List<Attachment> attachments;
        /**
         * List of recipients.
         */
        List<String> recipients;
        /**
         * Message size (bytes).
         */
        Integer size;
        /**
         * Scheduled for (timestamp, int64).
         */
        @JsonProperty("scheduled-for")
        Long scheduledFor;
    }

    /**
     * Message headers object for Mailgun Logs API.
     */
    @Value
    @Builder
    @Jacksonized
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MessageHeaders {
        /**
         * To address.
         */
        String to;
        /**
         * Message ID.
         */
        @JsonProperty("message-id")
        String messageId;
        /**
         * From address.
         */
        String from;
        /**
         * Subject.
         */
        String subject;
    }

    /**
     * Attachment object for Mailgun Logs API.
     */
    @Value
    @Builder
    @Jacksonized
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Attachment {
        /**
         * Filename.
         */
        String filename;
        /**
         * Content type.
         */
        @JsonProperty("content-type")
        String contentType;
        /**
         * Size (int32).
         */
        Integer size;
    }

    /**
     * Flags object for Mailgun Logs API.
     */
    @Value
    @Builder
    @Jacksonized
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Flags {
        /**
         * Is authenticated. Required.
         */
        @JsonProperty("is-authenticated")
        Boolean isAuthenticated;
        /**
         * Is system test. Required.
         */
        @JsonProperty("is-system-test")
        Boolean isSystemTest;
        /**
         * Is routed. Required.
         */
        @JsonProperty("is-routed")
        Boolean isRouted;
        /**
         * Is AMP.
         */
        @JsonProperty("is-amp")
        Boolean isAmp;
        /**
         * Is test mode. Required.
         */
        @JsonProperty("is-test-mode")
        Boolean isTestMode;
        /**
         * Is delayed bounce. Required.
         */
        @JsonProperty("is-delayed-bounce")
        Boolean isDelayedBounce;
        /**
         * Is callback. Required.
         */
        @JsonProperty("is-callback")
        Boolean isCallback;
        /**
         * Is encrypted.
         */
        @JsonProperty("is-encrypted")
        Boolean isEncrypted;
    }

    /**
     * GeoLocation object for Mailgun Logs API.
     */
    @Value
    @Builder
    @Jacksonized
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GeoLocation {
        String city;
        String country;
        String region;
        String timezone;
    }

    /**
     * ClientInfo object for Mailgun Logs API.
     */
    @Value
    @Builder
    @Jacksonized
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ClientInfo {
        @JsonProperty("client-name")
        String clientName;
        @JsonProperty("client-os")
        String clientOs;
        @JsonProperty("client-type")
        String clientType;
        @JsonProperty("device-type")
        String deviceType;
        @JsonProperty("user-agent")
        String userAgent;
        String ip;
        String bot;
    }

    /**
     * Routes object for Mailgun Logs API.
     */
    @Value
    @Builder
    @Jacksonized
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Routes {
        String actions;
        String description;
        String expression;
        String id;
        Integer priority;
        RoutesMatch match;
    }

    /**
     * RoutesMatch object for Mailgun Logs API.
     */
    @Value
    @Builder
    @Jacksonized
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RoutesMatch {
        String recipient;
    }

    /**
     * MailingList object for Mailgun Logs API.
     */
    @Value
    @Builder
    @Jacksonized
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MailingList {
        String address;
        @JsonProperty("list-id")
        String listId;
        String sid;
    }
} 
