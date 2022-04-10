package com.mailgun.model.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mailgun.enums.DomainState;
import com.mailgun.enums.SpamAction;
import com.mailgun.enums.WebScheme;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;

import static com.mailgun.util.Constants.RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NAME;

/**
 * Domain information
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-domains.html#domains">Domains</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Domain {

    /**
     * <p>
     * Domain's id
     * </p>
     */
    String id;

    /**
     * <p>
     * Flag whether the domain is disabled.
     * </p>
     */
    @JsonProperty("is_disabled")
    Boolean isDisabled;

    /**
     * <p>
     * Name of the domain (ex. domain.com)ю
     * </p>
     */
    String name;

    /**
     * <p>
     * <code>true</code> or <code>false</code>
     * </p>
     *
     * <p>
     * If set to <code>true</code>, this requires the message only be sent over a TLS connection.
     * </p>
     * <p>
     * If a TLS connection can not be established, Mailgun will not deliver the message.
     * </p>
     *
     * <p>
     * If set to <code>false</code>, Mailgun will still try and upgrade the connection,
     * but if Mailgun cannot, the message will be delivered over a plaintext SMTP connection.
     * </p>
     */
    @JsonProperty("require_tls")
    Boolean requireTls;

    /**
     * <p>
     * <code>true</code> or <code>false</code>
     * </p>
     *
     * <p>
     * If set to <code>true</code>, the certificate and hostname will not be verified when trying to establish a TLS connection
     * and Mailgun will accept any certificate during delivery.
     * </p>
     *
     * <p>
     * If set to <code>false</code>, Mailgun will verify the certificate and hostname.
     * If either one can not be verified, a TLS connection will not be established.
     * </p>
     */
    @JsonProperty("skip_verification")
    Boolean skipVerification;

    @JsonProperty("smtp_login")
    String smtpLogin;

    /**
     * <p>
     * Spam action: <code>disabled</code>, <code>block</code> or <code>tag</code>.
     * {@link SpamAction}
     * </p>
     *
     * <p>
     * If <code>disabled</code>, no spam filtering will occur for inbound messages.
     * </p>
     * <p>
     * If <code>block</code>, inbound spam messages will not be delivered.
     * </p>
     * <p>
     * If <code>tag</code>, inbound messages will be tagged with a spam header.
     * </p>
     * </p>
     */
    @JsonProperty("spam_action")
    SpamAction spamAction;

    /**
     * <p>
     * Domain's state
     * </p>
     *
     * <p>
     * Can be <code>active</code>, <code>unverified</code> or <code>disabled</code>
     * </p>
     * {@link DomainState}
     */
    DomainState state;

    /**
     * <p>
     * Domain's type
     * </p>
     */
    String type;

    /**
     * <p>
     * The tracking CNAME for a domain.
     * </p>
     */
    @JsonProperty("web_prefix")
    String webPrefix;

    /**
     * <p>
     * Set your open, click and unsubscribe URLs to use <code>http</code> or <code>https</code>.
     * </p>
     * {@link WebScheme}
     */
    @JsonProperty("web_scheme")
    WebScheme webScheme;

    /**
     * <p>
     * Determines whether the domain will accept email for sub-domains when sending messages.
     * <code>true</code> or <code>false</code>
     * </p>
     */
    Boolean wildcard;

    /**
     * <p>
     * Domain creation time.
     * </p>
     * {@link ZonedDateTime}
     */
    @JsonFormat(pattern = RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NAME)
    @JsonProperty("created_at")
    ZonedDateTime createdAt;

}
