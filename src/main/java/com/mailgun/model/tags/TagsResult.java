package com.mailgun.model.tags;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mailgun.model.Paging;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * <p>
 * Tags list result.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-tags.html#tags">Tags</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class TagsResult {

    /**
     * <p>
     * Tags list.
     * </p>
     */
    List<TagsItem> items;

    /**
     * <p>
     * Provides pagination urls.
     * </p>
     * {@link Paging}
     */
    Paging paging;

}
