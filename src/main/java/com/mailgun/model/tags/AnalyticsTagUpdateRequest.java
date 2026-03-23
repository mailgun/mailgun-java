package com.mailgun.model.tags;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * JSON request body for updating the description of an account-level tag
 * (PUT /v1/analytics/tags).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/tags-new/put-v1-analytics-tags.md">Update account tag</a>
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnalyticsTagUpdateRequest {

    /**
     * The tag to update.
     */
    String tag;

    /**
     * The updated tag description.
     */
    String description;

}
