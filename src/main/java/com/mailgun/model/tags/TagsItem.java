package com.mailgun.model.tags;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.Instant;

/**
 * <p>
 * Single tag information.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-tags.html#tags">Tags</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class TagsItem {

    /**
     * <p>
     * Name of the tag.
     * </p>
     */
    String tag;

    /**
     * <p>
     * Optional description of a tag.
     * </p>
     */
    String description;

    /**
     * <p>
     * First seen.
     * </p>
     * {@link Instant}
     */
    @JsonProperty("first-seen")
    Instant firstSeen;

    /**
     * <p>
     * Last seen.
     * </p>
     * {@link Instant}
     */
    @JsonProperty("last-seen")
    Instant lastSeen;

}
