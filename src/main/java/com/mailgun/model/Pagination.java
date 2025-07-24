package com.mailgun.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * Pagination information.
 * </p>
 */
@Getter
@Setter
@ToString
public class Pagination {

    /**
     * <p>
     * Colon-separated value indicating column name and sort direction e.g. 'domain:asc'.
     * </p>
     */
    String sort;

    /**
     * <p>
     * The number of items to skip over when satisfying the request. To get the first page of data set skip to zero. Then increment the skip by the limit for subsequent calls.
     * </p>
     */
    Integer skip;

    /**
     * <p>
     * The maximum number of items returned in the response.
     * </p>
     */
    Integer limit;

    /**
     * <p>
     * The total number of items in the query result set.
     * </p>
     */
    Integer total;
}
