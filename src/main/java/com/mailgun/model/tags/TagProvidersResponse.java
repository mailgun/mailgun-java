package com.mailgun.model.tags;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

/**
 * <p>
 * List of email providers for different event types.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-tags.html#tags">Tags</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class TagProvidersResponse {

    /**
     * <p>
     * List of email providers for different event types.
     * </p>
     */
    Map<String, Map<String, Integer>> provider;

    /**
     * <p>
     * Name of the tag.
     * </p>
     */
    String tag;

}
