package com.mailgun.model.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Domain tracking settings (open, click, unsubscribe) and web scheme.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-tracking/get-v3-domains--name--tracking">Get tracking settings</a>
 */
@Value
@Jacksonized
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DomainTracking {

    /**
     * <p>
     * Domain click tracking settings status.
     * </p>
     * {@link DomainTrackingStatus}
     */
    DomainTrackingStatus click;

    /**
     * <p>
     * Domain open tracking settings status (includes {@code place_at_the_top} for pixel placement).
     * </p>
     * {@link DomainTrackingStatus}
     */
    DomainTrackingStatus open;

    /**
     * <p>
     * Domain unsubscribe tracking settings status (active, html_footer, text_footer).
     * </p>
     * {@link DomainTrackingUnsubscribeStatus}
     */
    DomainTrackingUnsubscribeStatus unsubscribe;

    /**
     * <p>
     * Web scheme for tracking URLs (e.g. http or https).
     * </p>
     */
    @JsonProperty("web_scheme")
    String webScheme;

}
