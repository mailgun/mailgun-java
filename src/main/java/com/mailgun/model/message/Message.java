package com.mailgun.model.message;

import java.io.File;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.mailgun.enums.YesNo;
import com.mailgun.enums.YesNoHtml;
import com.mailgun.util.CollectionUtil;
import com.mailgun.util.DateTimeUtil;
import com.mailgun.util.StringUtil;
import feign.form.FormData;
import feign.form.FormProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static com.mailgun.util.Constants.FIELD_CANNOT_BE_NULL_OR_EMPTY;

/**
 * The object is used for sending messages(emails) using Mailgun API.
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-sending.html#sending">field-explanation</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class Message {

    /**
     * <p>
     * Email address for From header.
     * </p>
     */
    String from;

    /**
     * <p>
     * Email address of the recipient(s).
     * </p>
     */
    Set<String> to;

    /**
     * <p>
     * Same as {@link #to} but for Cc.
     * </p>
     */
    Set<String> cc;

    /**
     * <p>
     * Same as {@link #to} but for Bcc.
     * </p>
     */
    Set<String> bcc;

    /**
     * <p>
     * Message subject.
     * </p>
     */
    String subject;

    /**
     * <p>
     * Body of the message. (text version)
     * </p>
     */
    String text;

    /**
     * <p>
     * Body of the message. (HTML version)
     * </p>
     */
    String html;

    /**
     * <p>
     * File attachment.
     * </p>
     * <p>
     * You can post multiple attachment values.
     * </p>
     */
    Set<File> attachment;

    /**
     * <p>
     * File attachments in form of {@link FormData}.
     * This object encapsulates a byte array and its associated content type.
     * </p>
     * <p>
     * You can post multiple attachment values.
     * </p>
     */
    @FormProperty("attachment")
    Set<FormData> formData;

    /**
     * <p>
     * Attachment with inline disposition.
     * </p>
     * <p>
     * You can post multiple inline values.
     * </p>
     */
    Set<File> inline;

    /**
     * <p>
     * Name of a template stored via template API.
     * </p>
     */
    String template;

    /**
     * <p>
     * Use this parameter to send a message to specific version of a template.
     * </p>
     */
    @FormProperty("t:version")
    String templateVersion;

    /**
     * <p>
     * Rendered template in the text part of the message in case of template sending.
     * </p>
     */
    @FormProperty("t:text")
    String renderTemplate;

    /**
     * <p>
     * Tag string.
     * </p>
     *
     * @see <a href="https://documentation.mailgun.com/en/latest/user_manual.html#tagging-1">Tagging</a>
     */
    @FormProperty("o:tag")
    Set<String> tag;

    /**
     * <p>
     * Enables/disables DKIM signatures on per-message basis.
     * </p>
     */
    @FormProperty("o:dkim")
    String dkim;

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
     * If set to <code>true</code>, this requires the message only be sent over a TLS connection.
     * If a TLS connection can not be established, Mailgun will not deliver the message.
     * <p>
     * If set to  <code>false</code>, Mailgun will still try and upgrade the connection,
     * but if Mailgun can not, the message will be delivered over a plaintext SMTP connection.
     * <p>
     * The default is <code>false</code>.
     * </p>
     */
    @FormProperty("o:require-tls")
    String requireTls;

    /**
     * <p>
     * If set to <code>true</code>, the certificate and hostname will not be verified when trying to establish a TLS connection
     * and Mailgun will accept any certificate during delivery.
     * <p>
     * If set to <code>false</code>, Mailgun will verify the certificate and hostname.
     * If either one can not be verified, a TLS connection will not be established.
     * <p>
     * The default is <code>false</code>.
     * </p>
     */
    @FormProperty("o:skip-verification")
    String skipVerification;

    /**
     * <p>
     * Specify Reply-To address
     * </p>
     */
    @FormProperty("h:Reply-To")
    String replyTo;

    /**
     * <p>
     * A valid JSON-encoded dictionary, where key is a plain recipient address and value is a dictionary
     * with variables that can be referenced in the message body.
     * </p>
     *
     * @see <a href="https://documentation.mailgun.com/en/latest/user_manual.html#batch-sending">Batch Sending</a>
     */
    @FormProperty("recipient-variables")
    String recipientVariables;

    /**
     * <p>
     * Allows to attach a custom JSON data to the message
     * </p>
     */
    @FormProperty("v:my-var")
    String myVar;

    /**
     * <p>
     * Mailgun’s templates use a fork of the very popular template engine handlebars.
     * </p>
     * <p>
     * Use this field to provide values for a substitution to render them in the template.
     * </p>
     */
    @FormProperty("t:variables")
    String mailgunVariables;

    @FormProperty("h:X-Email-ID")
    String xoEmailIdOnHeader;

    public static MessageBuilder builder() {
        return new CustomMessageBuilder();
    }

    private static class CustomMessageBuilder extends MessageBuilder {

        public Message build() {
            if (StringUtils.isBlank(super.from)) {
                throw new IllegalArgumentException(String.format(FIELD_CANNOT_BE_NULL_OR_EMPTY, "from"));
            }

            if (CollectionUtils.isEmpty(super.to)) {
                throw new IllegalArgumentException(String.format(FIELD_CANNOT_BE_NULL_OR_EMPTY, "to"));
            }

            super.to.stream()
                    .filter(StringUtils::isNotBlank)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException(String.format(FIELD_CANNOT_BE_NULL_OR_EMPTY, "to")));

            if (CollectionUtils.isNotEmpty(super.attachment) && CollectionUtils.isNotEmpty(super.formData)) {
                throw new IllegalArgumentException("You cannot use 'attachment' and 'formData' together");
            }

            return super.build();
        }
    }

    public static class MessageBuilder {

        /**
         * <p>
         * Email address of the recipient
         * </p>
         *
         * @param to Email address of the recipient
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public Message.MessageBuilder to(String to) {
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
        public Message.MessageBuilder to(List<String> to) {
            this.to = CollectionUtil.addToSet(this.to, to);
            return this;
        }

        /**
         * <p>
         * Same as {@link #to} but for Cc.
         * </p>
         *
         * @param cc Same as {@link #to} but for Cc.
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public Message.MessageBuilder cc(String cc) {
            this.cc = CollectionUtil.addToSet(this.cc, cc);
            return this;
        }

        /**
         * <p>
         * Same as {@link #to} but for Cc.
         * </p>
         *
         * @param cc Same as {@link #to} but for Cc.
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public Message.MessageBuilder cc(List<String> cc) {
            this.cc = CollectionUtil.addToSet(this.cc, cc);
            return this;
        }

        /**
         * <p>
         * Same as {@link #to} but for Bcc.
         * </p>
         *
         * @param bcc Same as {@link #to} but for Bcc.
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public Message.MessageBuilder bcc(String bcc) {
            this.bcc = CollectionUtil.addToSet(this.bcc, bcc);
            return this;
        }

        /**
         * <p>
         * Same as {@link #to} but for Bcc.
         * </p>
         *
         * @param bcc Same as {@link #to} but for Bcc.
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public Message.MessageBuilder bcc(List<String> bcc) {
            this.bcc = CollectionUtil.addToSet(this.bcc, bcc);
            return this;
        }

        /**
         * <p>
         * File attachment.
         * You can post multiple attachment values.
         * </p>
         *
         * @param attachment File attachment
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public Message.MessageBuilder attachment(File attachment) {
            this.attachment = CollectionUtil.addToSet(this.attachment, attachment);
            return this;
        }

        /**
         * <p>
         * File attachment.
         * You can post multiple attachment values.
         * </p>
         *
         * @param attachments File attachments
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public Message.MessageBuilder attachment(List<File> attachments) {
            this.attachment = CollectionUtil.addToSet(this.attachment, attachments);
            return this;
        }

        /**
         * <p>
         * File attachment in form of {@link FormData}.
         * This object encapsulates a byte array and its associated content type.
         * </p>
         * You can post multiple attachment values.
         *
         * @param attachment attachment in form of {@link FormData}.
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public Message.MessageBuilder formData(FormData attachment) {
            this.formData = CollectionUtil.addToSet(this.formData, attachment);
            return this;
        }

        /**
         * <p>
         * File attachment in form of {@link FormData}.
         * This object encapsulates a byte array and its associated content type.
         * </p>
         * You can post multiple attachment values.
         *
         * @param attachments attachments in form of {@link FormData}.
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public Message.MessageBuilder formData(List<FormData> attachments) {
            this.formData = CollectionUtil.addToSet(this.formData, attachments);
            return this;
        }

        /**
         * <p>
         * Attachment with inline disposition.
         * You can post multiple inline values.
         * </p>
         *
         * @param attachment Attachment with inline disposition.
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public Message.MessageBuilder inline(File attachment) {
            this.inline = CollectionUtil.addToSet(this.inline, attachment);
            return this;
        }

        /**
         * <p>
         * Attachments with inline disposition.
         * You can post multiple inline values.
         * </p>
         *
         * @param attachments Attachments with inline disposition.
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public Message.MessageBuilder inline(List<File> attachments) {
            this.inline = CollectionUtil.addToSet(this.inline, attachments);
            return this;
        }

        /**
         * <p>
         * Rendered template in the text part of the message in case of template sending.
         * </p>
         *
         * @param renderTemplate Pass <code>true</code> if you want to have rendered template in the text part of the message in case of template sending.
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public MessageBuilder renderTemplate(boolean renderTemplate) {
            if (renderTemplate) {
                this.renderTemplate = YesNo.YES.getValue();
            }
            return this;
        }

        /**
         * <p>
         * Tag string.
         * </p>
         *
         * @param tag Tag string.
         * @return Returns a reference to this object so that method calls can be chained together.
         * @see <a href="https://documentation.mailgun.com/en/latest/user_manual.html#tagging-1">Tagging</a>
         */
        public Message.MessageBuilder tag(String tag) {
            this.tag = CollectionUtil.addToSet(this.tag, tag);
            return this;
        }

        /**
         * <p>
         * Tag string.
         * </p>
         *
         * @param tags Tag strings.
         * @return Returns a reference to this object so that method calls can be chained together.
         * @see <a href="https://documentation.mailgun.com/en/latest/user_manual.html#tagging-1">Tagging</a>
         */
        public Message.MessageBuilder tag(List<String> tags) {
            this.tag = CollectionUtil.addToSet(this.tag, tags);
            return this;
        }

        /**
         * <p>
         * Enables/disables DKIM signatures on per-message basis.
         * </p>
         *
         * @param enableDKIM Enables/disables DKIM signatures on per-message basis.
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public MessageBuilder dkim(boolean enableDKIM) {
            this.dkim = YesNo.getValue(enableDKIM);
            return this;
        }

        /**
         * <p>
         * Desired time of delivery.
         * </p>
         * <p>
         * Note: Messages can be scheduled for a maximum of 3 days in the future.
         * </p>
         *
         * @param deliveryTime Desired time of delivery.
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public MessageBuilder deliveryTime(ZonedDateTime deliveryTime) {
            this.deliveryTime = DateTimeUtil.toString(deliveryTime);
            return this;
        }

        /**
         * <p>
         * Enables sending in test mode.
         * </p>
         *
         * @param enableSendingInTestMode Enables sending in test mode.
         * @return Returns a reference to this object so that method calls can be chained together.
         * @see <a href="https://documentation.mailgun.com/en/latest/user_manual.html#manual-testmode">Sending in Test Mode</a>
         */
        public MessageBuilder testMode(boolean enableSendingInTestMode) {
            if (enableSendingInTestMode) {
                this.testMode = YesNo.YES.getValue();
            }
            return this;
        }

        /**
         * <p>
         * Toggles tracking on a per-message basis.
         * </p>
         *
         * @param toggleTracking Toggles tracking on a per-message basis.
         * @return Returns a reference to this object so that method calls can be chained together.
         * @see <a href="https://documentation.mailgun.com/en/latest/user_manual.html#tracking-messages">Tracking Messages</a>
         */
        public MessageBuilder tracking(boolean toggleTracking) {
            this.tracking = YesNo.getValue(toggleTracking);
            return this;
        }

        /**
         * <p>
         * Toggles clicks tracking on a per-message basis.
         * Has higher priority than domain-level setting.
         * </p>
         * <p>
         * Pass <code>TRUE</code>, <code>FALSE</code> or <code>HTML_ONLY</code>
         * </p>
         *
         * @param yesNoHtml {@link YesNoHtml}
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public MessageBuilder trackingClicks(YesNoHtml yesNoHtml) {
            this.trackingClicks = yesNoHtml.getValue();
            return this;
        }

        /**
         * <p>
         * Toggles opens tracking on a per-message basis.
         * Has higher priority than domain-level setting.
         * </p>
         *
         * @param toggleOpensTracking Toggles opens tracking on a per-message basis.
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public MessageBuilder trackingOpens(boolean toggleOpensTracking) {
            this.trackingOpens = YesNo.getValue(toggleOpensTracking);
            return this;
        }

        /**
         * <p>
         * If set to <code>true</code> this requires the message only be sent over a TLS connection.
         * If a TLS connection can not be established, Mailgun will not deliver the message.
         * <p>
         * If set to  <code>false</code> Mailgun will still try and upgrade the connection,
         * but if Mailgun can not, the message will be delivered over a plaintext SMTP connection.
         * <p>
         * The default is <code>false</code>.
         * </p>
         *
         * @param requireTLS requires TLS
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public MessageBuilder requireTls(boolean requireTLS) {
            this.requireTls = YesNo.getValue(requireTLS);
            return this;
        }

        /**
         * <p>
         * If set to <code>true</code>, the certificate and hostname will not be verified when trying to establish a TLS connection
         * and Mailgun will accept any certificate during delivery.
         * <p>
         * If set to <code>false</code>, Mailgun will verify the certificate and hostname.
         * If either one can not be verified, a TLS connection will not be established.
         * <p>
         * The default is <code>false</code>.
         * </p>
         *
         * @param skipVerification skip verification certificate and hostname
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public MessageBuilder skipVerification(boolean skipVerification) {
            this.skipVerification = YesNo.getValue(skipVerification);
            return this;
        }

        /**
         * <p>
         * Specify Reply-To address
         * </p>
         *
         * @param replyTo Reply-To address
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public MessageBuilder replyTo(String replyTo) {
            this.replyTo = replyTo;
            return this;
        }

        /**
         * <p>
         * A dictionary, where the key is a plain recipient address
         * and the value is a dictionary with variables that can be referenced in the message body.
         * </p>
         *
         * @param recipientVariables A dictionary, where the key is a plain recipient address
         *                           and the value is a dictionary with variables that can be referenced in the message body.
         * @return Returns a reference to this object so that method calls can be chained together.
         * @see <a href="https://documentation.mailgun.com/en/latest/user_manual.html#batch-sending">Batch Sending</a>
         */
        public MessageBuilder recipientVariables(Map<String, Object> recipientVariables) {
            this.recipientVariables = StringUtil.asJsonString(recipientVariables);
            return this;
        }

        /**
         * <p>
         * A dictionary, where the key is a plain recipient address
         * and the value is a dictionary with variables that can be referenced in the message body.
         * </p>
         *
         * @param recipientVariables A valid JSON-encoded dictionary, where the key is a plain recipient address
         *                           and the value is a dictionary with variables that can be referenced in the message body.
         * @return Returns a reference to this object so that method calls can be chained together.
         * @see <a href="https://documentation.mailgun.com/en/latest/user_manual.html#batch-sending">Batch Sending</a>
         */
        public MessageBuilder recipientVariables(String recipientVariables) {
            this.recipientVariables = recipientVariables;
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
        public MessageBuilder myVar(Map<String, Object> myVar) {
            this.myVar = StringUtil.asJsonString(myVar);
            return this;
        }

        /**
         * <p>
         * Allows to attach a custom data to the message
         * </p>
         *
         * @param myVar A valid JSON-encoded dictionary
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public MessageBuilder myVar(String myVar) {
            this.myVar = myVar;
            return this;
        }

        /**
         * <p>
         * Mailgun’s templates use a fork of the very popular template engine handlebars.
         * </p>
         * <p>
         * Use this field to provide values for a substitution to render them in the template.
         * </p>
         *
         * @param mailgunVariables A dictionary where the key is a placeholder and the value is a value for a substitution.
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public MessageBuilder mailgunVariables(Map<String, Object> mailgunVariables) {
            this.mailgunVariables = StringUtil.asJsonString(mailgunVariables);
            return this;
        }

        /**
         * <p>
         * Mailgun’s templates use a fork of the very popular template engine handlebars.
         * </p>
         * <p>
         * Use this field to provide values for a substitution to render them in the template.
         * </p>
         *
         * @param mailgunVariables A valid JSON-encoded dictionary, where the key is a placeholder and
         *                         the value is a value for a substitution.
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public MessageBuilder mailgunVariables(String mailgunVariables) {
            this.mailgunVariables = mailgunVariables;
            return this;
        }
    }

}
