package com.mailgun.model.stats;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Failed events.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-stats.html#stats">Stats</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Failed {

    /**
     * <p>
     * Permanent failed events.
     * </p>
     * {@link Permanent}
     */
    Permanent permanent;

    /**
     * <p>
     * Temporary failed events.
     * </p>
     * {@link Temporary}
     */
    Temporary temporary;

}
