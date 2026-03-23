package com.mailgun.model.tags;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Query parameters for {@code GET /v3/{domain}/tags} (list all tags).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/tags/get-v3--domain--tags.md">List all tags</a>
 * @deprecated Use the new Tags API instead.
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class TagsQuery {

    /**
     * Page direction: first, last, next, or prev.
     */
    String page;

    /**
     * Maximum number of items to return.
     */
    Integer limit;

    /**
     * The tag that marks the boundary of the current page (pagination cursor).
     */
    String tag;

    /**
     * List only tags that begin with this prefix.
     */
    String prefix;

}
