package com.mailgun.model.tags;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Devices for different event types.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-tags.html#tags">Tags</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Devices {

    /**
     * <p>
     * Desktop.
     * </p>
     */
    DeviceEventTypes desktop;

    /**
     * <p>
     * Mobile.
     * </p>
     */
    DeviceEventTypes mobile;

    /**
     * <p>
     * Tablet.
     * </p>
     */
    DeviceEventTypes tablet;

    /**
     * <p>
     * Unknown.
     * </p>
     */
    DeviceEventTypes unknown;

}
