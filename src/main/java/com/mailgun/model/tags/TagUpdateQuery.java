package com.mailgun.model.tags;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * Query parameters for {@code PUT /v3/{domain}/tag} (update tag description).
 * {@code description} is optional; null values are omitted from the query string.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/tags/put-v3--domain--tag.md">Update tag</a>
 * @deprecated Use the new Tags API instead.
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class TagUpdateQuery {

    /**
     * The name of the tag to update.
     */
    @NonNull
    String tag;

    /**
     * Updated description for the tag.
     */
    String description;

}
