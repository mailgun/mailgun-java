package com.mailgun.model.tags;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * List of devices for different event types.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-tags.html#tags">Tags</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class TagDevicesResponse {

    /**
     * <p>
     * List of devices for different event types.
     * </p>
     */
    Devices device;

    /**
     * <p>
     * Name of the tag.
     * </p>
     */
    String tag;

}
