package com.mailgun.model.stats;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Permanent failed events.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-stats.html#stats">Stats</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Permanent {

    /**
     * <p>
     * Recipients previously bounced.
     * </p>
     */
    @JsonProperty("suppress-bounce")
    Integer suppressBounce;

    /**
     * <p>
     * Recipients previously unsubscribed.
     * </p>
     */
    @JsonProperty("suppress-unsubscribe")
    Integer suppressUnsubscribe;

    /**
     * <p>
     * Recipients previously complained.
     * </p>
     */
    @JsonProperty("suppress-complaint")
    Integer suppressComplaint;

    Integer bounce;

    @JsonProperty("delayed-bounce")
    Integer delayedBounce;

    Integer webhook;

    Integer optimized;

    /**
     * <p>
     * Failed permanently and dropped.
     * </p>
     */
    Integer total;

}
