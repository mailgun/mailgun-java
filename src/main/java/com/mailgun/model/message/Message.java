package com.mailgun.model.message;

import java.io.File;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mailgun.form.CustomProperties;
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
 * Multipart form model for {@code POST /v3/{domain_name}/messages} (Mailgun Sending API 3.0.0).
 * <p>
 * You must provide one of: {@code text}, {@code html}, {@code amp-html}, or {@code template}.
 * Send options (parameter names starting with {@code o:}, {@code h:}, {@code v:}, or {@code t:}) are limited to
 * {@value com.mailgun.util.Constants#MAILGUN_SEND_OPTIONS_MAX_BYTES} bytes in total.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/messages/post-v3--domain-name--messages">Send an email</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class Message {

    /**
     * Email address of the {@code From} header (optional friendly name: {@code "Friendly Name <addr@example.com>"}).
     * Not required when sending with a template that defines {@code From}, but any value here overrides the template.
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
     * Message subject. Not required when the template defines a subject; a provided value overrides the template.
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
     * AMP part of the message. Follow Google guidelines to compose and send AMP emails.
     * </p>
     *
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/messages/post-v3--domain-name--messages">Send an email</a>
     */
    @FormProperty("amp-html")
    String ampHtml;

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
     * Attachment with inline disposition (for example inline images). You can post multiple {@code inline} parts.
     */
    Set<File> inline;

    /**
     * Inline attachments as {@link FormData} (same form field name as file-based {@link #inline}).
     */
    @FormProperty("inline")
    Set<FormData> inlineFormData;

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
     * When set to {@code yes}, generates a plain-text MIME part from the template alongside HTML ({@code t:text=yes}).
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
     * Second domain key for DKIM signing, formatted as {@code signing_domain/selector} (for example {@code example.com/s1}).
     */
    @FormProperty("o:secondary-dkim")
    String secondaryDkim;

    /**
     * Public alias for {@link #secondaryDkim}, formatted as {@code public_signing_domain/selector}.
     * Requires {@link #secondaryDkim} to be set.
     */
    @FormProperty("o:secondary-dkim-public")
    String secondaryDkimPublic;

    /**
     * Scheduled delivery time in RFC-2822 format. Plan and domain {@code message_ttl} limit how far ahead you can schedule.
     */
    @FormProperty("o:deliverytime")
    String deliveryTime;

    /**
     * Maximum time window for delivering the message, for example {@code 1h30m}, {@code 30m}, or {@code 24h}
     * (minimum {@code 5m}, maximum {@code 24h}). For scheduled mail, the window starts at the scheduled time.
     */
    @FormProperty("o:deliver-within")
    String deliverWithin;

    /**
     * Send Time Optimization window, for example {@code 24h} (minimum {@code 24h}, maximum {@code 72h}).
     */
    @FormProperty("o:deliverytime-optimize-period")
    String deliveryTimeOptimizePeriod;

    /**
     * Timezone Optimization preferred local delivery time ({@code HH:mm} or {@code hh:mmaa}).
     */
    @FormProperty("o:time-zone-localize")
    String timeZoneLocalize;

    /**
     * <p>
     * X-Mailgun-Deliver-Within header - delivery deadline for the message.
     * </p>
     * <p>
     * Expects a Golang encoded duration value in range [5m;24h].
     * </p>
     * <p>
     * If specified, an explicit deadline is set for the corresponding smtp2 job
     * and deliver-by field is added to the fired accepted event with the deadline value.
     * </p>
     */
    @FormProperty("h:X-Mailgun-Deliver-Within")
    String xMailgunDeliverWithin;

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
     * Dedicated sending IP owned by your account.
     */
    @FormProperty("o:sending-ip")
    String sendingIp;

    /**
     * IP pool identifier; the message is sent from an IP in that pool.
     */
    @FormProperty("o:sending-ip-pool")
    String sendingIpPool;

    /**
     * Places the open-tracking pixel at the top of the HTML message ({@code yes}, {@code no}, {@code true}, {@code false}, {@code htmlonly}).
     */
    @FormProperty("o:tracking-pixel-location-top")
    String trackingPixelLocationTop;

    /**
     * Comma-separated {@code X-Mailgun} header names to strip from the delivered message, or {@code all} for all such headers.
     */
    @FormProperty("o:suppress-headers")
    String suppressHeaders;

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
	 * Specify Sender address
	 * </p>
	 */
	@FormProperty("h:Sender")
	String sender;

    /**
     * JSON-encoded map of recipient address to per-recipient variables (batch sending; up to 1,000 recipients per batch).
     *
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/user-manual/sending-messages/batch-sending">Batch Sending</a>
     */
    @FormProperty("recipient-variables")
    String recipientVariables;

    /**
     * Example single {@code v:} user variable ({@code v:my-var}). For multiple variables prefer {@link #userVariables}.
     */
    @FormProperty("v:my-var")
    String myVar;

    /**
     * Additional {@code v:} variables: map keys are names without the {@code v:} prefix (for example {@code "user-id"} → {@code v:user-id}).
     */
    @CustomProperties(prefix = "v:")
    Map<String, String> userVariables;

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

    /**
     * <p>
     * Specify custom email headers
     * </p>
     */
    @CustomProperties(prefix = "h:")
    Map<String, String> headers;

    public static MessageBuilder builder() {
        return new CustomMessageBuilder();
    }

    private static class CustomMessageBuilder extends MessageBuilder {

        public Message build() {
            if (StringUtils.isBlank(super.from) && StringUtils.isBlank(super.template)) {
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

            if (CollectionUtils.isNotEmpty(super.inline) && CollectionUtils.isNotEmpty(super.inlineFormData)) {
                throw new IllegalArgumentException("You cannot use 'inline' and 'inlineFormData' together");
            }

            boolean hasBody = StringUtils.isNotBlank(super.text)
                || StringUtils.isNotBlank(super.html)
                || StringUtils.isNotBlank(super.ampHtml)
                || StringUtils.isNotBlank(super.template);
            if (!hasBody) {
                throw new IllegalArgumentException(
                    "At least one of 'text', 'html', 'amp-html', or 'template' must be provided");
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
         * Inline attachment as {@link FormData} (cannot be combined with file-based {@link #inline}).
         */
        public Message.MessageBuilder inlineFormData(FormData inline) {
            this.inlineFormData = CollectionUtil.addToSet(this.inlineFormData, inline);
            return this;
        }

        /**
         * Inline attachments as {@link FormData} (cannot be combined with file-based {@link #inline}).
         */
        public Message.MessageBuilder inlineFormData(List<FormData> inlines) {
            this.inlineFormData = CollectionUtil.addToSet(this.inlineFormData, inlines);
            return this;
        }

        /**
         * <p>
         * AMP part of the message. Follow Google guidelines to compose and send AMP emails.
         * </p>
         *
         * @param ampHtml AMP HTML content
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public Message.MessageBuilder ampHtml(String ampHtml) {
            this.ampHtml = ampHtml;
            return this;
        }

        /**
         * Sets {@code t:text=yes} so Mailgun adds a {@code text/plain} part derived from the template.
         *
         * @param renderTemplate when {@code true}, sets {@code t:text} to {@code yes}
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
         * Second DKIM signing domain and selector ({@code signing_domain/selector}).
         */
        public MessageBuilder secondaryDkim(String secondaryDkim) {
            this.secondaryDkim = secondaryDkim;
            return this;
        }

        /**
         * Public signing domain alias for secondary DKIM ({@code public_signing_domain/selector}).
         */
        public MessageBuilder secondaryDkimPublic(String secondaryDkimPublic) {
            this.secondaryDkimPublic = secondaryDkimPublic;
            return this;
        }

        /**
         * Scheduled delivery time (RFC-2822). Maximum advance scheduling depends on your plan and domain settings.
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
         * Delivery deadline for the message.
         * </p>
         * <p>
         * Expects a Golang encoded duration value in range [5m;24h].
         * Examples: "5m", "30m", "1h", "12h", "24h".
         * </p>
         * <p>
         * If specified, an explicit deadline is set for the corresponding smtp2 job
         * and deliver-by field is added to the fired accepted event with the deadline value.
         * </p>
         *
         * @param deliverWithin Golang encoded duration value (e.g., "5m", "1h", "24h")
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public MessageBuilder deliverWithin(String deliverWithin) {
            this.deliverWithin = deliverWithin;
            return this;
        }

        /**
         * Send Time Optimization window (for example {@code 24h}; minimum {@code 24h}, maximum {@code 72h}).
         */
        public MessageBuilder deliveryTimeOptimizePeriod(String deliveryTimeOptimizePeriod) {
            this.deliveryTimeOptimizePeriod = deliveryTimeOptimizePeriod;
            return this;
        }

        /**
         * Timezone Optimization preferred local delivery time ({@code HH:mm} or {@code hh:mmaa}).
         */
        public MessageBuilder timeZoneLocalize(String timeZoneLocalize) {
            this.timeZoneLocalize = timeZoneLocalize;
            return this;
        }

        /**
         * <p>
         * X-Mailgun-Deliver-Within header - delivery deadline for the message.
         * </p>
         * <p>
         * Expects a Golang encoded duration value in range [5m;24h].
         * Examples: "5m", "30m", "1h", "12h", "24h".
         * </p>
         * <p>
         * If specified, an explicit deadline is set for the corresponding smtp2 job
         * and deliver-by field is added to the fired accepted event with the deadline value.
         * </p>
         *
         * @param xMailgunDeliverWithin Golang encoded duration value (e.g., "5m", "1h", "24h")
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public MessageBuilder xMailgunDeliverWithin(String xMailgunDeliverWithin) {
            this.xMailgunDeliverWithin = xMailgunDeliverWithin;
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
         * Dedicated sending IP for this message.
         */
        public MessageBuilder sendingIp(String sendingIp) {
            this.sendingIp = sendingIp;
            return this;
        }

        /**
         * Sending IP pool identifier.
         */
        public MessageBuilder sendingIpPool(String sendingIpPool) {
            this.sendingIpPool = sendingIpPool;
            return this;
        }

        /**
         * Whether to place the open-tracking pixel at the top of HTML messages
         * ({@code yes}, {@code no}, {@code true}, {@code false}, {@code htmlonly}).
         */
        public MessageBuilder trackingPixelLocationTop(YesNoHtml trackingPixelLocationTop) {
            this.trackingPixelLocationTop = trackingPixelLocationTop.getValue();
            return this;
        }

        /**
         * Same as {@link #trackingPixelLocationTop(YesNoHtml)} with a raw API value.
         */
        public MessageBuilder trackingPixelLocationTop(String trackingPixelLocationTop) {
            this.trackingPixelLocationTop = trackingPixelLocationTop;
            return this;
        }

        /**
         * Headers to remove from the delivered message (comma-separated names or {@code all}).
         */
        public MessageBuilder suppressHeaders(String suppressHeaders) {
            this.suppressHeaders = suppressHeaders;
            return this;
        }

        /**
         * <p>
         * Sends a copy of successfully delivered messages to the specified URL via HTTP POST.
         * The request uses Content-Type: application/mime and contains the exact message the recipient's SMTP server received.
         * </p>
         * <p>
         * NOTE: These are accounted for and billed as delivered messages
         * </p>
         *
         * @param archiveTo copy emails to this URL
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public MessageBuilder archiveTo(String archiveTo) {
            this.archiveTo = archiveTo;
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
		 * Specify Sender address
		 * </p>
		 *
		 * @param sender Sender address
		 * @return Returns a reference to this object so that method calls can be chained together.
		 */
		public MessageBuilder sender(String sender) {
			this.sender = sender;
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
         * Per-message {@code v:} variables (keys without the {@code v:} prefix).
         */
        public MessageBuilder userVariables(Map<String, String> userVariables) {
            this.userVariables = userVariables;
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

        /**
         * <p>
         * Specify custom email headers
         * </p>
         *
         * @param headers custom email headers
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public MessageBuilder headers(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }
    }

}
