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
 * Request for creating or updating an account-level webhook.
 * Both create (POST /v1/webhooks) and update (PUT /v1/webhooks/{webhook_id}) share the same fields.
 * Use the builder's {@code eventType(String)} method to add individual event types,
 * e.g. {@code eventType(WebhookName.CLICKED.getValue())}.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/account-webhooks/post-v1-webhooks.md">Create an account-level webhook</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/account-webhooks/put-v1-webhooks--webhook-id-.md">Update an account-level webhook</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class AccountWebhookRequest {

    /**
     * Optional description for the webhook.
     */
    @FormProperty("description")
    String description;

    /**
     * Event types to subscribe to (e.g. "accepted", "clicked", "delivered").
     * Use the builder's singular {@code eventType(String)} to add individual types,
     * or pass {@link com.mailgun.enums.WebhookName#getValue()} for type safety.
     * Maximum of 3 unique URLs per event type.
     */
    @Singular
    @FormProperty("event_types")
    Set<String> eventTypes;

    /**
     * URL for the webhook to be sent to.
     */
    @FormProperty("url")
    String url;

    /** Merged by Lombok; present so Javadoc can resolve the builder type without running APs. */
    public static class AccountWebhookRequestBuilder {
    }

    public static AccountWebhookRequestBuilder builder() {
        return new CustomAccountWebhookRequestBuilder();
    }

    private static class CustomAccountWebhookRequestBuilder extends AccountWebhookRequestBuilder {

        @Override
        public AccountWebhookRequest build() {
            if (CollectionUtils.isEmpty(super.eventTypes)) {
                throw new IllegalArgumentException(String.format(FIELD_CANNOT_BE_NULL_OR_EMPTY, "event_types"));
            }
            if (StringUtils.isBlank(super.url)) {
                throw new IllegalArgumentException(String.format(FIELD_CANNOT_BE_NULL_OR_EMPTY, "url"));
            }
            return super.build();
        }
    }

}
