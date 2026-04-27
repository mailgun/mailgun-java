package com.mailgun.model.mailing.lists;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mailgun.model.Paging;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * <p>
 * Mailing list response.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-mailinglists.html#mailing-lists">Mailing Lists</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/mailing-lists/get-v3-lists.md">Get mailing lists</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/mailing-lists/get-v3-lists-pages.md">Get mailing lists by page</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MailingListDataResponse {

    /**
     * Total lists in the account (present on {@code GET /v3/lists}, not on paginated {@code /lists/pages}).
     */
    @JsonProperty("total_count")
    Integer totalCount;

    /**
     * <p>
     * List of {@link MailingListData}.
     * </p>
     */
    List<MailingListData> items;

    /**
     * <p>
     * Provides pagination urls.
     * </p>
     * {@link Paging}
     */
    Paging paging;

}
