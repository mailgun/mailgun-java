package com.mailgun.model.message;

import java.io.File;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mailgun.enums.YesNo;
import com.mailgun.enums.YesNoHtml;
import com.mailgun.form.CustomProperties;
import com.mailgun.util.CollectionUtil;
import com.mailgun.util.DateTimeUtil;
import com.mailgun.util.StringUtil;
import feign.form.FormData;
import feign.form.FormProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import static com.mailgun.util.Constants.FIELD_CANNOT_BE_NULL_OR_EMPTY;

/**
 * Multipart form model for {@code POST /v3/{domain_name}/messages.mime} (Mailgun Sending API 3.0.0).
 * <p>
 * Supply the raw MIME document as the {@code message} form field using {@code multipart/form-data}
 * ({@link File} via {@link #message} or bytes/filename via {@link #messageFormData}).
 * Send options (parameter names starting with {@code o:}, {@code h:}, {@code v:}, or {@code t:}) are limited to
 * {@value com.mailgun.util.Constants#MAILGUN_SEND_OPTIONS_MAX_BYTES} bytes in total.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/messages/post-v3--domain-name--messages-mime">Send an email in MIME format</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class MailgunMimeMessage {

    /**
     * Recipient address(es); friendly names supported.
     */
    Set<String> to;

    /**
     * MIME document uploaded as a file (form field {@code message}). Mutually exclusive with {@link #messageFormData}.
     */
    File message;

    /**
     * MIME document as {@link FormData} (same form field name {@code message}). Mutually exclusive with {@link #message}.
     */
    @FormProperty("message")
    FormData messageFormData;

    /**
     * Template name from the Templates API; optional with raw MIME, required when using template rendering options.
     *
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/user-manual/sending-messages/send-templates">Templates</a>
     */
    String template;

    /**
     * Renders a specific template version; {@link #template} must also be set.
     */
    @FormProperty("t:version")
    String templateVersion;

    /**
     * When set to {@code yes}, Mailgun adds a {@code text/plain} part from the template ({@code t:text=yes}).
     */
    @FormProperty("t:text")
    String renderTemplate;

    /**
     * JSON-encoded dictionary for template variable expansion.
     *
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/user-manual/sending-messages/send-templates">Templates</a>
     */
    @FormProperty("t:variables")
    String mailgunVariables;

    /**
     * Tag strings for analytics and routing.
     *
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/user-manual/tracking-messages/track-tagging">Tags</a>
     */
    @FormProperty("o:tag")
    Set<String> tag;

    /**
     * Enables or disables DKIM for this message ({@code yes}, {@code no}, {@code true}, {@code false}).
     */
    @FormProperty("o:dkim")
    String dkim;

    @FormProperty("o:secondary-dkim")
    String secondaryDkim;

    @FormProperty("o:secondary-dkim-public")
    String secondaryDkimPublic;

    /**
     * Scheduled delivery time (RFC-2822). Maximum advance scheduling depends on your plan and domain settings.
     */
    @FormProperty("o:deliverytime")
    String deliveryTime;

    /**
     * Maximum delivery window, for example {@code 1h30m} or {@code 24h} (minimum {@code 5m}, maximum {@code 24h}).
     */
    @FormProperty("o:deliver-within")
    String deliverWithin;

    /**
     * Send Time Optimization window, for example {@code 24h} (minimum {@code 24h}, maximum {@code 72h}).
     *
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/user-manual/sending-messages/send-sto">Sending a Message with STO</a>
     */
    @FormProperty("o:deliverytime-optimize-period")
    String deliveryTimeOptimizePeriod;

    /**
     * Timezone Optimization preferred local delivery time ({@code HH:mm} or {@code hh:mmaa}).
     *
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/user-manual/sending-messages/send-tzo">Sending a Message with TZO</a>
     */
    @FormProperty("o:time-zone-localize")
    String timeZoneLocalize;

    @FormProperty("h:X-Mailgun-Deliver-Within")
    String xMailgunDeliverWithin;

    /**
     * Test mode: messages are accepted and processed but not delivered to recipients.
     *
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/user-manual/sending-messages/test-mode">Sending in Test Mode</a>
     */
    @FormProperty("o:testmode")
    String testMode;

    /**
     * Toggles click and open tracking ({@code yes}, {@code no}, {@code true}, {@code false}, {@code htmlonly}).
     *
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/user-manual/tracking-messages">Tracking Messages</a>
     */
    @FormProperty("o:tracking")
    String tracking;

    /**
     * Click tracking override ({@code yes}, {@code no}, {@code true}, {@code false}, {@code htmlonly}).
     *
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/user-manual/tracking-messages/tracking-clicks">Tracking Clicks</a>
     */
    @FormProperty("o:tracking-clicks")
    String trackingClicks;

    /**
     * Open tracking override ({@code yes}, {@code no}, {@code true}, {@code false}).
     *
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/user-manual/tracking-messages/tracking-opens">Tracking Opens</a>
     */
    @FormProperty("o:tracking-opens")
    String trackingOpens;

    @FormProperty("o:require-tls")
    String requireTls;

    @FormProperty("o:skip-verification")
    String skipVerification;

    @FormProperty("o:sending-ip")
    String sendingIp;

    @FormProperty("o:sending-ip-pool")
    String sendingIpPool;

    @FormProperty("o:tracking-pixel-location-top")
    String trackingPixelLocationTop;

    /**
     * Comma-separated {@code X-Mailgun} header names to strip, or {@code all}.
     */
    @FormProperty("o:suppress-headers")
    String suppressHeaders;

    /**
     * POST successfully delivered MIME to this URL ({@code Content-Type: application/mime}); billed as delivered.
     */
    @FormProperty("o:archive-to")
    String archiveTo;

    @FormProperty("h:Reply-To")
    String replyTo;

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
    /**
     * Example {@code v:} variable; prefer {@link #userVariables} for multiple keys.
     */
    @FormProperty("v:my-var")
    String myVar;

    /**
     * Additional {@code v:} variables (keys without the {@code v:} prefix).
     */
    @CustomProperties(prefix = "v:")
    Map<String, String> userVariables;

    /**
     * Custom {@code h:} headers (map keys are names without the {@code h:} prefix).
     */
    @CustomProperties(prefix = "h:")
    Map<String, String> headers;

    public static MailgunMimeMessageBuilder builder() {
        return new CustomMailgunMimeMessageBuilder();
    }

    private static class CustomMailgunMimeMessageBuilder extends MailgunMimeMessageBuilder {

        @Override
        public MailgunMimeMessage build() {
            if (CollectionUtils.isEmpty(super.to)) {
                throw new IllegalArgumentException(String.format(FIELD_CANNOT_BE_NULL_OR_EMPTY, "to"));
            }
            super.to.stream()
                    .filter(StringUtils::isNotBlank)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException(String.format(FIELD_CANNOT_BE_NULL_OR_EMPTY, "to")));

            if (super.message == null && super.messageFormData == null) {
                throw new IllegalArgumentException(String.format(FIELD_CANNOT_BE_NULL_OR_EMPTY, "message"));
            }
            if (super.message != null && super.messageFormData != null) {
                throw new IllegalArgumentException("You cannot use 'message' (file) and 'messageFormData' together");
            }

            return super.build();
        }
    }

    public static class MailgunMimeMessageBuilder {

        public MailgunMimeMessage.MailgunMimeMessageBuilder to(String to) {
            this.to = CollectionUtil.addToSet(this.to, to);
            return this;
        }

        public MailgunMimeMessage.MailgunMimeMessageBuilder to(List<String> to) {
            this.to = CollectionUtil.addToSet(this.to, to);
            return this;
        }

        public MailgunMimeMessage.MailgunMimeMessageBuilder tag(String tag) {
            this.tag = CollectionUtil.addToSet(this.tag, tag);
            return this;
        }

        public MailgunMimeMessage.MailgunMimeMessageBuilder tag(List<String> tags) {
            this.tag = CollectionUtil.addToSet(this.tag, tags);
            return this;
        }

        public MailgunMimeMessageBuilder dkim(boolean enableDkim) {
            this.dkim = YesNo.getValue(enableDkim);
            return this;
        }

        public MailgunMimeMessageBuilder secondaryDkim(String secondaryDkim) {
            this.secondaryDkim = secondaryDkim;
            return this;
        }

        public MailgunMimeMessageBuilder secondaryDkimPublic(String secondaryDkimPublic) {
            this.secondaryDkimPublic = secondaryDkimPublic;
            return this;
        }

        public MailgunMimeMessageBuilder deliveryTime(ZonedDateTime deliveryTime) {
            this.deliveryTime = DateTimeUtil.toString(deliveryTime);
            return this;
        }

        public MailgunMimeMessage.MailgunMimeMessageBuilder deliverWithin(String deliverWithin) {
            this.deliverWithin = deliverWithin;
            return this;
        }

        public MailgunMimeMessageBuilder deliveryTimeOptimizePeriod(String deliveryTimeOptimizePeriod) {
            this.deliveryTimeOptimizePeriod = deliveryTimeOptimizePeriod;
            return this;
        }

        public MailgunMimeMessageBuilder timeZoneLocalize(String timeZoneLocalize) {
            this.timeZoneLocalize = timeZoneLocalize;
            return this;
        }

        public MailgunMimeMessage.MailgunMimeMessageBuilder xMailgunDeliverWithin(String xMailgunDeliverWithin) {
            this.xMailgunDeliverWithin = xMailgunDeliverWithin;
            return this;
        }

        /**
         * @see <a href="https://documentation.mailgun.com/docs/mailgun/user-manual/sending-messages/test-mode">Sending in Test Mode</a>
         */
        public MailgunMimeMessageBuilder testMode(boolean enableTestMode) {
            if (enableTestMode) {
                this.testMode = YesNo.YES.getValue();
            }
            return this;
        }

        /**
         * Toggles tracking using {@code yes} or {@code no}. For {@code htmlonly} (or other API values), use {@link #tracking(YesNoHtml)}.
         */
        public MailgunMimeMessageBuilder tracking(boolean toggleTracking) {
            this.tracking = YesNo.getValue(toggleTracking);
            return this;
        }

        /**
         * Toggles click and open tracking, including {@link YesNoHtml#HTML_ONLY}.
         *
         * @see <a href="https://documentation.mailgun.com/docs/mailgun/user-manual/tracking-messages">Tracking Messages</a>
         */
        public MailgunMimeMessageBuilder tracking(YesNoHtml tracking) {
            this.tracking = tracking.getValue();
            return this;
        }

        /**
         * @see <a href="https://documentation.mailgun.com/docs/mailgun/user-manual/tracking-messages/tracking-clicks">Tracking Clicks</a>
         */
        public MailgunMimeMessageBuilder trackingClicks(YesNoHtml yesNoHtml) {
            this.trackingClicks = yesNoHtml.getValue();
            return this;
        }

        /**
         * @see <a href="https://documentation.mailgun.com/docs/mailgun/user-manual/tracking-messages/tracking-opens">Tracking Opens</a>
         */
        public MailgunMimeMessageBuilder trackingOpens(boolean toggleOpensTracking) {
            this.trackingOpens = YesNo.getValue(toggleOpensTracking);
            return this;
        }

        public MailgunMimeMessageBuilder requireTls(boolean requireTls) {
            this.requireTls = YesNo.getValue(requireTls);
            return this;
        }

        public MailgunMimeMessageBuilder skipVerification(boolean skipVerification) {
            this.skipVerification = YesNo.getValue(skipVerification);
            return this;
        }

        public MailgunMimeMessageBuilder sendingIp(String sendingIp) {
            this.sendingIp = sendingIp;
            return this;
        }

        public MailgunMimeMessageBuilder sendingIpPool(String sendingIpPool) {
            this.sendingIpPool = sendingIpPool;
            return this;
        }

        public MailgunMimeMessageBuilder trackingPixelLocationTop(YesNoHtml trackingPixelLocationTop) {
            this.trackingPixelLocationTop = trackingPixelLocationTop.getValue();
            return this;
        }

        public MailgunMimeMessageBuilder trackingPixelLocationTop(String trackingPixelLocationTop) {
            this.trackingPixelLocationTop = trackingPixelLocationTop;
            return this;
        }

        public MailgunMimeMessageBuilder suppressHeaders(String suppressHeaders) {
            this.suppressHeaders = suppressHeaders;
            return this;
        }

        public MailgunMimeMessageBuilder archiveTo(String archiveTo) {
            this.archiveTo = archiveTo;
            return this;
        }

        public MailgunMimeMessageBuilder replyTo(String replyTo) {
            this.replyTo = replyTo;
            return this;
        }

        public MailgunMimeMessageBuilder renderTemplate(boolean renderTemplate) {
            if (renderTemplate) {
                this.renderTemplate = YesNo.YES.getValue();
            }
            return this;
        }

        public MailgunMimeMessageBuilder recipientVariables(Map<String, Object> recipientVariables) {
            this.recipientVariables = StringUtil.asJsonString(recipientVariables);
            return this;
        }

        public MailgunMimeMessageBuilder recipientVariables(String recipientVariables) {
            this.recipientVariables = recipientVariables;
            return this;
        }

        public MailgunMimeMessage.MailgunMimeMessageBuilder myVar(Map<String, Object> myVar) {
            this.myVar = StringUtil.asJsonString(myVar);
            return this;
        }

        public MailgunMimeMessageBuilder myVar(String myVar) {
            this.myVar = myVar;
            return this;
        }

        public MailgunMimeMessageBuilder userVariables(Map<String, String> userVariables) {
            this.userVariables = userVariables;
            return this;
        }

        public MailgunMimeMessageBuilder mailgunVariables(Map<String, Object> mailgunVariables) {
            this.mailgunVariables = StringUtil.asJsonString(mailgunVariables);
            return this;
        }

        public MailgunMimeMessageBuilder mailgunVariables(String mailgunVariables) {
            this.mailgunVariables = mailgunVariables;
            return this;
        }

        public MailgunMimeMessageBuilder headers(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }
    }
}
