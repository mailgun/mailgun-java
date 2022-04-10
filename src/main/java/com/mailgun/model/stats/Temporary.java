package com.mailgun.model.stats;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Temporary failed events.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-stats.html#stats">Stats</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Temporary {

    /**
     * <p>
     * Failed temporary due to ESP block, will be retried.
     * </p>
     */
    Integer espblock;

    /**
     * <p>
     * Failed permanently and dropped.
     * </p>
     */
    Integer total;

}
