package com.mailgun.model.webhooks;

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
 * Webhook update request.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-webhooks.html#webhooks">Webhooks</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class WebhookUpdateRequest {

    /**
     * <p>
     * URLs for the webhook event.
     * Only up to 3 URLs are supported.
     * </p>
     */
    @Singular
    @FormProperty("url")
    Set<String> urls;

    public static WebhookUpdateRequestBuilder builder() {
        return new CustomWebhookUpdateRequestBuilder();
    }

    private static class CustomWebhookUpdateRequestBuilder extends WebhookUpdateRequestBuilder {

        public WebhookUpdateRequest build() {
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

    public static class WebhookUpdateRequestBuilder {
    }

}
