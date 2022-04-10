package com.mailgun.model.stats;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Accepted events.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-stats.html#stats">Stats</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Accepted {

    /**
     * <p>
     * Unauthenticated.
     * </p>
     */
    Integer incoming;

    /**
     * <p>
     * Authenticated.
     * </p>
     */
    Integer outgoing;

    /**
     * <p>
     * Total.
     * </p>
     */
    Integer total;

}
