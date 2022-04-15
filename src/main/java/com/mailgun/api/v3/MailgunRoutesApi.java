package com.mailgun.api.v3;

import com.mailgun.api.MailgunApi;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.routes.Route;
import com.mailgun.model.routes.RoutesListResponse;
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
 * @see <a href="https://documentation.mailgun.com/en/latest/api-routes.html">Routes</a>
 */
public interface MailgunRoutesApi extends MailgunApi {

    /**
     * <p>
     * Fetches the list of routes (limit to 100 entries).
     * </p>
     * Note: that routes are defined globally, per account, not per domain as most of other API calls.
     *
     * @return {@link RoutesListResponse}
     */
    @Headers("Accept: application/json")
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
    @Headers("Accept: application/json")
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
    @Headers("Accept: application/json")
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
    @Headers("Accept: application/json")
    @RequestLine("GET /routes")
    Response getRoutesListFeignResponse(@QueryMap RoutesPageRequest pageRequest);

    /**
     * <p>
     * Returns a single route object based on its ID.
     * </p>
     *
     * @param id ID of the route
     * @return {@link SingleRouteResponse}
     */
    @Headers("Accept: application/json")
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
    @Headers("Accept: application/json")
    @RequestLine("GET /routes/{id}")
    Response getSingleRouteFeignResponse(@Param("id") String id);

    /**
     * <p>
     * Creates a new route.
     * </p>
     *
     * @param request {@link RoutesRequest}
     * @return {@link RoutesResponse}
     */
    @Headers({"Content-Type: multipart/form-data", "Accept: application/json"})
    @RequestLine("POST /routes")
    RoutesResponse createRoute(RoutesRequest request);

    /**
     * <p>
     * Creates a new route.
     * </p>
     *
     * @param request {@link RoutesRequest}
     * @return {@link Response}
     */
    @Headers({"Content-Type: multipart/form-data", "Accept: application/json"})
    @RequestLine("POST /routes")
    Response createRouteFeignResponse(RoutesRequest request);

    /**
     * <p>
     * Updates a given route by ID.
     * </p>
     * All parameters are optional: this API call only updates the specified fields leaving others unchanged.
     *
     * @param id      ID of the route
     * @param request {@link RoutesRequest}
     * @return {@link Route}
     */
    @Headers({"Content-Type: multipart/form-data", "Accept: application/json"})
    @RequestLine("PUT /routes/{id}")
    Route updateRoute(@Param("id") String id, RoutesRequest request);

    /**
     * <p>
     * Updates a given route by ID.
     * </p>
     * All parameters are optional: this API call only updates the specified fields leaving others unchanged.
     *
     * @param id      ID of the route
     * @param request {@link RoutesRequest}
     * @return {@link Response}
     */
    @Headers({"Content-Type: multipart/form-data", "Accept: application/json"})
    @RequestLine("PUT /routes/{id}")
    Response updateRouteFeignResponse(@Param("id") String id, RoutesRequest request);

    /**
     * <p>
     * Deletes a route based on the id.
     * </p>
     *
     * @param id ID of the route
     * @return {@link ResponseWithMessage}
     */
    @Headers({"Accept: application/json"})
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
    @Headers({"Accept: application/json"})
    @RequestLine("DELETE /routes/{id}")
    Response deleteRouteFeignResponse(@Param("id") String id);

}
