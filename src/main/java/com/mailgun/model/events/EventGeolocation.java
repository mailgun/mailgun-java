package com.mailgun.model.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Event geolocation.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-events.html">Events API</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventGeolocation {

    /**
     * <p>
     * Country.
     * </p>
     */
    String country;

    /**
     * <p>
     * Region.
     * </p>
     */
    String region;

    /**
     * <p>
     * City.
     * </p>
     */
    String city;

}
