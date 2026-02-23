package com.mailgun.model.domains;

import com.mailgun.enums.SpamAction;
import com.mailgun.enums.WebScheme;
import feign.form.FormProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Request body for updating a domain (PUT /v4/domains/{name}).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domains/put-v4-domains--name-">Update domain</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class DomainUpdateRequest {

    /**
     * If set to a URL, each successfully delivered message will be POSTed to that URL (Content-Type: application/mime).
     */
    @FormProperty("archive_to")
    String archiveTo;

    /**
     * The hostname to update to. Must be lower case.
     */
    @FormProperty("mailfrom_host")
    String mailfromHost;

    /**
     * Time-to-live in seconds for retrieving incoming and outgoing messages. Max value depends on subscription plan.
     */
    @FormProperty("message_ttl")
    Integer messageTtl;

    /**
     * If true, messages for the domain are sent only over TLS. Default: false.
     */
    @FormProperty("require_tls")
    Boolean requireTls;

    /**
     * If true, certificate and hostname are not verified for TLS. Default: false.
     */
    @FormProperty("skip_verification")
    Boolean skipVerification;

    /**
     * Updates the domain's SMTP credentials password.
     */
    @FormProperty("smtp_password")
    String smtpPassword;

    /**
     * Spam action: disabled, tag, or block.
     */
    @FormProperty("spam_action")
    String spamAction;

    /**
     * Enable or disable Automatic Sender Security. Domain must be reverified after changing. Default: false.
     */
    @FormProperty("use_automatic_sender_security")
    Boolean useAutomaticSenderSecurity;

    /**
     * Open, click and unsubscribe URLs scheme: http or https. Default: http.
     */
    @FormProperty("web_scheme")
    String webScheme;

    /**
     * Web prefix for tracking (click, open, unsubscribe). Must be a valid atom. Requires matching CNAME in DNS.
     */
    @FormProperty("web_prefix")
    String webPrefix;

    /**
     * Whether the domain is a wildcard domain (can receive for any subdomain).
     */
    Boolean wildcard;

    public static class DomainUpdateRequestBuilder {

        /**
         * Spam action: disabled, tag, or block.
         */
        public DomainUpdateRequestBuilder spamAction(SpamAction spamAction) {
            this.spamAction = spamAction != null ? spamAction.getValue() : null;
            return this;
        }

        /**
         * Open, click and unsubscribe URLs scheme: http or https.
         */
        public DomainUpdateRequestBuilder webScheme(WebScheme webScheme) {
            this.webScheme = webScheme != null ? webScheme.getValue() : null;
            return this;
        }
    }
}
