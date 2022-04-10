package com.mailgun.model.webhooks;

import com.mailgun.enums.WebhookName;
import feign.form.FormProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

import static com.mailgun.util.Constants.FIELD_CANNOT_BE_NULL_OR_EMPTY;

/**
 * <p>
 * Webhook request.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-webhooks.html#webhooks">Webhooks</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class WebhookRequest {

    /**
     * <p>
     * Name of the webhook.
     * </p>
     */
    @FormProperty("id")
    String webhookName;

    /**
     * <p>
     * URLs for the webhook event.
     * Only up to 3 URLs are supported.
     * </p>
     */
    @Singular
    @FormProperty("url")
    Set<String> urls;

    public static WebhookRequestBuilder builder() {
        return new CustomWebhookRequestBuilder();
    }

    private static class CustomWebhookRequestBuilder extends WebhookRequestBuilder {

        public WebhookRequest build() {
            if (CollectionUtils.isEmpty(super.urls)) {
                throw new IllegalArgumentException(String.format(FIELD_CANNOT_BE_NULL_OR_EMPTY, "url(s)"));
            }

            super.urls.stream()
                    .filter(StringUtils::isNotBlank)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException(String.format(FIELD_CANNOT_BE_NULL_OR_EMPTY, "url(s)")));

            return super.build();
        }
    }

    public static class WebhookRequestBuilder {

        /**
         * <p>
         * Name of the webhook.
         * </p>
         * <p>
         * Note: When adding a <code>CLICKED</code> or <code>OPENED</code> webhook, ensure that you also have tracking enabled.
         * </p>
         *
         * @param webhookName Name of the webhook {@link WebhookName}.
         * @return Returns a reference to this object.
         */
        public WebhookRequest.WebhookRequestBuilder webhookName(WebhookName webhookName) {
            this.webhookName = webhookName.getValue();
            return this;
        }
    }

}
