package com.mailgun.model.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Member.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-events.html">Events API</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventMember {

    /**
     * <p>
     * Event member name.
     * </p>
     */
    String name;

    /**
     * <p>
     * Event member address.
     * </p>
     */
    String address;

    /**
     * <p>
     * Whether the event participant subscribed.
     * </p>
     */
    Boolean subscribed;

}
