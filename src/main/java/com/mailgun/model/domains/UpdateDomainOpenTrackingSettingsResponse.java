package com.mailgun.model.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Response for PUT /v3/domains/{name}/tracking/open (message and open settings including place_at_the_top).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-tracking/put-v3-domains--name--tracking-open">Update open tracking settings</a>
 */
@Value
@Jacksonized
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateDomainOpenTrackingSettingsResponse {

    /**
     * <p>
     * Result status message.
     * </p>
     */
    String message;

    /**
     * <p>
     * Domain tracking settings status.
     * </p>
     * {@link DomainTrackingStatus}
     */
    DomainTrackingStatus open;

}
