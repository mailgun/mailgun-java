package com.mailgun.api.v3;

import com.mailgun.api.MailgunApi;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.routes.Route;
import com.mailgun.model.routes.RoutesListResponse;
import com.mailgun.model.routes.RoutesMatchRequest;
import com.mailgun.model.routes.RoutesPageRequest;
import com.mailgun.model.routes.RoutesRequest;
import com.mailgun.model.routes.RoutesResponse;
import com.mailgun.model.routes.SingleRouteResponse;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import feign.Response;

/**
 * <p>
 * Mailgun Routes Api
 * </p>
 * <p>
 * Mailgun Routes are a powerful way to handle the incoming traffic.
 * </p>
 * <p>
 * See
 * <a href="https://documentation.mailgun.com/en/latest/user_manual.html#um-routes">user-manual/Routes</a>
 * section in the User Manual to learn more about how they work.
 * </p>
 * <p>
 * This API allows you to work with routes programmatically.
 * <p>
 * Routes are comprised of the following arguments:
 * </p>
 * <pre>
 * A filter (when to do something).
 * A priority (in what order).
 * An action (what to do).
 * </pre>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/routes">Routes</a>
 */
@Headers("Accept: application/json")
public interface MailgunRoutesApi extends MailgunApi {

    /**
     * <p>
     * Fetches the list of routes (limit to 100 entries).
     * </p>
     * <p>
     * Get the list of routes. Note that routes are defined globally, per account, not per domain.
     * </p>
     *
     * @return {@link RoutesListResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/routes/get-v3-routes">Get all routes</a>
     */
    @RequestLine("GET /routes")
    RoutesListResponse getRoutesList();

    /**
     * <p>
     * Fetches the list of routes (limit to 100 entries).
     * </p>
     * Note: that routes are defined globally, per account, not per domain as most of other API calls.
     *
     * @return {@link Response}
     */
    @RequestLine("GET /routes")
    Response getRoutesListFeignResponse();

    /**
     * <p>
     * Fetches the list of routes.
     * </p>
     * Note: that routes are defined globally, per account, not per domain as most of other API calls.
     *
     * @param pageRequest {@link RoutesPageRequest}
     * @return {@link RoutesListResponse}
     */
    @RequestLine("GET /routes")
    RoutesListResponse getRoutesList(@QueryMap RoutesPageRequest pageRequest);

    /**
     * <p>
     * Fetches the list of routes.
     * </p>
     * Note: that routes are defined globally, per account, not per domain as most of other API calls.
     *
     * @param pageRequest {@link RoutesPageRequest}
     * @return {@link Response}
     */
    @RequestLine("GET /routes")
    Response getRoutesListFeignResponse(@QueryMap RoutesPageRequest pageRequest);

    /**
     * <p>
     * Returns a single route object based on its ID.
     * </p>
     * <p>
     * Returns a detailed view of the route.
     * </p>
     *
     * @param id The unique identifier of the route
     * @return {@link SingleRouteResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/routes/get-v3-routes-id">Get a route</a>
     */
    @RequestLine("GET /routes/{id}")
    SingleRouteResponse getSingleRoute(@Param("id") String id);

    /**
     * <p>
     * Returns a single route object based on its ID.
     * </p>
     *
     * @param id ID of the route
     * @return {@link Response}
     */
    @RequestLine("GET /routes/{id}")
    Response getSingleRouteFeignResponse(@Param("id") String id);

    /**
     * <p>
     * Creates a new route.
     * </p>
     * <p>
     * Adds a new route to the account.
     * </p>
     *
     * @param request {@link RoutesRequest}
     * @return {@link RoutesResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/routes/post-v3-routes">Create a route</a>
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /routes")
    RoutesResponse createRoute(RoutesRequest request);

    /**
     * <p>
     * Creates a new route.
     * </p>
     * <p>
     * Adds a new route to the account.
     * </p>
     *
     * @param request {@link RoutesRequest}
     * @return {@link Response}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/routes/post-v3-routes">Create a route</a>
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /routes")
    Response createRouteFeignResponse(RoutesRequest request);

    /**
     * <p>
     * Updates a given route by ID.
     * </p>
     * <p>
     * Updates a given route. All parameters are optional. This only updates the specified fields, leaving others unchanged.
     * </p>
     *
     * @param id      ID of the route
     * @param request {@link RoutesRequest}
     * @return {@link Route}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/routes/put-v3-routes-id">Update a route</a>
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /routes/{id}")
    Route updateRoute(@Param("id") String id, RoutesRequest request);

    /**
     * <p>
     * Updates a given route by ID.
     * </p>
     * <p>
     * Updates a given route. All parameters are optional. This only updates the specified fields, leaving others unchanged.
     * </p>
     *
     * @param id      ID of the route
     * @param request {@link RoutesRequest}
     * @return {@link Response}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/routes/put-v3-routes-id">Update a route</a>
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /routes/{id}")
    Response updateRouteFeignResponse(@Param("id") String id, RoutesRequest request);

    /**
     * <p>
     * Deletes a route based on the id.
     * </p>
     * <p>
     * Remove the route from the account.
     * </p>
     *
     * @param id ID of the route
     * @return {@link ResponseWithMessage}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/routes/delete-v3-routes-id">Delete a route</a>
     */
    @RequestLine("DELETE /routes/{id}")
    ResponseWithMessage deleteRoute(@Param("id") String id);

    /**
     * <p>
     * Deletes a route based on the id.
     * </p>
     *
     * @param id ID of the route
     * @return {@link Response}
     */
    @RequestLine("DELETE /routes/{id}")
    Response deleteRouteFeignResponse(@Param("id") String id);

    /**
     * <p>
     * Checks if an address matches at least one route.
     * </p>
     * <p>
     * Returns the first matching route if found, or 404 if no route matches.
     * </p>
     *
     * @param matchRequest {@link RoutesMatchRequest} containing the address to match
     * @return {@link SingleRouteResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/routes/get-v3-routes-match">Match address to route</a>
     */
    @RequestLine("GET /routes/match")
    SingleRouteResponse matchRoute(@QueryMap RoutesMatchRequest matchRequest);

    /**
     * <p>
     * Checks if an address matches at least one route.
     * </p>
     * <p>
     * Returns the first matching route if found, or 404 if no route matches.
     * </p>
     *
     * @param matchRequest {@link RoutesMatchRequest} containing the address to match
     * @return {@link Response}
     */
    @RequestLine("GET /routes/match")
    Response matchRouteFeignResponse(@QueryMap RoutesMatchRequest matchRequest);

}
