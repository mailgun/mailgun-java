package com.mailgun.model.stats;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Delivered events.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-stats.html#stats">Stats</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Delivered {

    /**
     * <p>
     * Delivered over SMTP.
     * </p>
     */
    Integer smtp;

    /**
     * <p>
     * Delivered over HTTP.
     * </p>
     */
    Integer http;

    /**
     * <p>
     * Optimized.
     * </p>
     */
    Integer optimized;

    /**
     * <p>
     * Total.
     * </p>
     */
    Integer total;

}
