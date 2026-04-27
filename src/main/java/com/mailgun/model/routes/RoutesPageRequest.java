package com.mailgun.model.routes;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Routes page request.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/routes/get-v3-routes.md">Get all routes</a>
 */
@Value
@Jacksonized
@Builder
public class RoutesPageRequest {

    /**
     * <p>
     * Maximum number of records to return.
     * </p>
     * Default number of entries to return: 100.
     */
    Integer limit;

    /**
     * <p>
     * Number of records to skip. (0 by default)
     * </p>
     */
    Integer skip;

}
