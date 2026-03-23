package com.mailgun.model.tags;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Response for {@code GET /v3/domains/{domain}/limits/tag}.
 * Contains the tag limit and current usage count for a domain.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/tags/get-v3-domains--domain--limits-tag.md">Get tag limits</a>
 * @deprecated Use the new Tags API instead.
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class TagLimitsResponse {

    /**
     * Identifier for the limit record.
     */
    String id;

    /**
     * Maximum number of unique tags allowed for the domain.
     */
    Integer limit;

    /**
     * Current number of unique tags in use.
     */
    Integer count;

}
