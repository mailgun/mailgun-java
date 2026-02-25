package com.mailgun.model.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Open/click tracking settings. For open tracking, {@code placeAtTheTop} indicates pixel placement.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-tracking/get-v3-domains--name--tracking">Get tracking settings</a>
 */
@Value
@Jacksonized
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DomainTrackingStatus {

    /**
     * Whether tracking is enabled.
     */
    Boolean active;

    /**
     * (Open tracking only) If true, the tracking pixel is placed at the top of the HTML body.
     */
    @JsonProperty("place_at_the_top")
    Boolean placeAtTheTop;

}
