package com.mailgun.model.tags;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * JSON request body for deleting an account-level tag (DELETE /v1/analytics/tags).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/tags-new/delete-v1-analytics-tags.md">Delete account tag</a>
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnalyticsTagDeleteRequest {

    /**
     * The tag to delete.
     */
    String tag;

}
