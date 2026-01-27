package com.mailgun.model.routes;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Routes match request.
 * </p>
 * <p>
 * Used to check if an address matches at least one route.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/routes/get-v3-routes-match">Match address to route</a>
 */
@Value
@Jacksonized
@Builder
public class RoutesMatchRequest {

    /**
     * <p>
     * Address to match routes on.
     * </p>
     */
    String address;

}
