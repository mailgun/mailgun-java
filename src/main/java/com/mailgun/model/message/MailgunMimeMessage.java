package com.mailgun.model.message;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mailgun.util.CollectionUtil;
import com.mailgun.util.StringUtil;
import feign.form.FormProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * The object is used for sending messages(emails) in MIME format using Mailgun API.
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-sending.html#sending">field-explanation</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class MailgunMimeMessage {

    /**
     * <p>
     * Email address of the recipient(s).
     * </p>
     */
    Set<String> to;

    /**
     * <p>
     * Same as {@link #to} but for Bcc.
     * </p>
     */
    File message;

    /**
     * <p>
     * Tag string.
     * </p>
     *
     * @see <a href="https://documentation.mailgun.com/en/latest/user_manual.html#tagging-1">Tagging</a>
     */
    @FormProperty("o:tag") Set<String> tag;

    /**
     * <p>
     * Desired time of delivery.
     * </p>
     *
     * <p>
     * Note: Messages can be scheduled for a maximum of 3 days in the future.
     * </p>
     */
    @FormProperty("o:deliverytime")
    String deliveryTime;

    /**
     * <p>
     * Enables/disables DKIM signatures on per-message basis.
     * </p>
     */
    @FormProperty("o:dkim")
    String dkim;

    /**
     * <p>
     * Enables sending in test mode.
     * </p>
     *
     * @see <a href="https://documentation.mailgun.com/en/latest/user_manual.html#manual-testmode">Sending in Test Mode</a>
     */
    @FormProperty("o:testmode")
    String testMode;

    /**
     * <p>
     * Toggles tracking on a per-message basis.
     * </p>
     *
     * @see <a href="https://documentation.mailgun.com/en/latest/user_manual.html#tracking-messages">Tracking Messages</a>
     */
    @FormProperty("o:tracking")
    String tracking;

    /**
     * <p>
     * Toggles clicks tracking on a per-message basis.
     * </p>
     * <p>
     * Has higher priority than domain-level setting.
     * </p>
     * <p>
     * Pass <code>TRUE</code>, <code>FALSE</code> or <code>HTML_ONLY</code>
     * </p>
     */
    @FormProperty("o:tracking-clicks")
    String trackingClicks;

    /**
     * <p>
     * Toggles opens tracking on a per-message basis.
     * </p>
     * <p>
     * Has higher priority than domain-level setting.
     * </p>
     */
    @FormProperty("o:tracking-opens")
    String trackingOpens;


    /**
     * <p>
     * Sends a copy of successfully delivered messages to the specified URL via HTTP POST.
     * The request uses Content-Type: application/mime and contains the exact message the recipient's SMTP server received.
     * </p>
     * <p>
     * NOTE: These are accounted for and billed as delivered messages
     * </p>
     */
    @FormProperty("o:archive-to")
    String archiveTo;

    /**
     * <p>
     * Specify Reply-To address
     * </p>
     */
    @FormProperty("h:Reply-To")
    String replyTo;

    /**
     * <p>
     * Allows to attach a custom JSON data to the message
     * </p>
     */
    @FormProperty("v:my-var")
    String myVar;

    public static class MailgunMimeMessageBuilder {

        /**
         * <p>
         * Email address of the recipient
         * </p>
         *
         * @param to Email address of the recipient
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public MailgunMimeMessage.MailgunMimeMessageBuilder to(String to) {
            this.to = CollectionUtil.addToSet(this.to, to);
            return this;
        }

        /**
         * <p>
         * Email addresses of the recipient
         * </p>
         *
         * @param to Email addresses of the recipient
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public MailgunMimeMessage.MailgunMimeMessageBuilder to(List<String> to) {
            this.to = CollectionUtil.addToSet(this.to, to);
            return this;
        }


        /**
         * <p>
         * Allows to attach a custom data to the message
         * </p>
         *
         * @param myVar dictionary
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public MailgunMimeMessage.MailgunMimeMessageBuilder myVar(Map<String, Object> myVar) {
            this.myVar = StringUtil.asJsonString(myVar);
            return this;
        }
    }

}
