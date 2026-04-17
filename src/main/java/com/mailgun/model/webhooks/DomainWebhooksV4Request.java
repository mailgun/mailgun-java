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
 * Request for creating or updating domain-level webhooks via the v4 API.
 * Associates one URL with one or more event types in a single operation.
 * Use the builder's singular {@code eventType(String)} to add individual types,
 * e.g. {@code eventType(WebhookName.CLICKED.getValue())}.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-webhooks/post-v4-domains--domain--webhooks.md">Create domain webhooks (v4)</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-webhooks/put-v4-domains--domain--webhooks.md">Update domain webhooks (v4)</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class DomainWebhooksV4Request {

    /**
     * The webhook URL that will receive POST requests.
     */
    @FormProperty("url")
    String url;

    /**
     * Event types to associate with this URL. Use the builder's singular {@code eventType(String)}
     * to add individual types. This replaces any existing event type associations for the URL on PUT.
     */
    @Singular
    @FormProperty("event_types")
    Set<String> eventTypes;

    /** Merged by Lombok; present so Javadoc can resolve the builder type without running APs. */
    public static class DomainWebhooksV4RequestBuilder {
    }

    public static DomainWebhooksV4RequestBuilder builder() {
        return new CustomDomainWebhooksV4RequestBuilder();
    }

    private static class CustomDomainWebhooksV4RequestBuilder extends DomainWebhooksV4RequestBuilder {

        @Override
        public DomainWebhooksV4Request build() {
            if (StringUtils.isBlank(super.url)) {
                throw new IllegalArgumentException(String.format(FIELD_CANNOT_BE_NULL_OR_EMPTY, "url"));
            }
            if (CollectionUtils.isEmpty(super.eventTypes)) {
                throw new IllegalArgumentException(String.format(FIELD_CANNOT_BE_NULL_OR_EMPTY, "event_types"));
            }
            return super.build();
        }
    }

}
