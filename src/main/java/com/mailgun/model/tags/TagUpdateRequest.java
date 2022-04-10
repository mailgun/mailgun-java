package com.mailgun.model.tags;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * <p>
 * Tag update request.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-tags.html#tags">Tags</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class TagUpdateRequest {

    /**
     * <p>
     * Name of the domain.
     * </p>
     */
    String domain;

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

}
