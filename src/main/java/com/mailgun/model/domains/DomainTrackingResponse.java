package com.mailgun.model.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Response for GET /v3/domains/{name}/tracking (open, click, unsubscribe, web_scheme).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-tracking/get-v3-domains--name--tracking">Get tracking settings</a>
 */
@Value
@Jacksonized
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DomainTrackingResponse {

    /**
     * <p>
     * Domain tracking settings statuses.
     * </p>
     * {@link DomainTracking}
     */
    DomainTracking tracking;

}
