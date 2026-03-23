package com.mailgun.model.bounceclassification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Pagination parameters for the bounce classification metrics request.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/bounce-classification/post-v2-bounce-classification-metrics.md">List statistic v2</a>
 */
@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class BounceClassificationPagination {

    /**
     * Colon-separated value indicating column name and sort direction e.g. 'entity-name:asc'.
     */
    String sort;

    /**
     * The number of items to skip over. To get the first page set to zero,
     * then increment by limit for subsequent pages.
     */
    Integer skip;

    /**
     * The maximum number of items returned in the response.
     */
    Integer limit;

}
