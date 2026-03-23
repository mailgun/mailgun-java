package com.mailgun.api.v3;

import com.mailgun.api.MailgunApi;
import com.mailgun.model.events.EventsQueryOptions;
import com.mailgun.model.events.EventsResponse;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import feign.Response;

/**
 * Events API (v3) — deprecated in favour of the
 * <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/logs">Logs API</a>.
 * <p>
 * Mailgun tracks every event that happens to your emails and makes this data available to you through the Events API.
 * Mailgun retains this detailed data for at least 3 days.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/events">Events API</a>
 * @deprecated Use the Logs API instead.
 */
@Headers("Accept: application/json")
public interface MailgunEventsApi extends MailgunApi {

    /**
     * <p>
     * Get all events that happen to your emails.
     * </p>
     *
     * @param domain Name of the domain
     * @return {@link EventsResponse}
     */
    @RequestLine("GET /{domain}/events")
    EventsResponse getAllEvents(@Param("domain") String domain);

    /**
     * <p>
     * Get all events that happen to your emails.
     * </p>
     *
     * @param domain Name of the domain
     * @return {@link Response}
     */
    @RequestLine("GET /{domain}/events")
    Response getAllEventsFeignResponse(@Param("domain") String domain);

    /**
     * <p>
     * Get specified events that happen to your emails.
     * </p>
     * <p>
     * A request should define a time range and can specify a set of filters to apply.
     * </p>
     * <p>
     * In response, a page of events is returned along with URLs(Page ID) that can be used to retrieve the next and previous result pages.
     * </p>
     * <p>
     * To traverse the entire range, you should keep requesting the next page URLs returned along with result pages until an empty result page is reached.
     * </p>
     *
     * @param domain       Name of the domain
     * @param queryOptions {@link EventsQueryOptions}
     * @return {@link EventsResponse}
     */
    @RequestLine("GET /{domain}/events")
    EventsResponse getEvents(@Param("domain") String domain, @QueryMap EventsQueryOptions queryOptions);

    /**
     * <p>
     * Get specified events that happen to your emails.
     * </p>
     * <p>
     * A request should define a time range and can specify a set of filters to apply.
     * </p>
     * <p>
     * In response, a page of events is returned along with URLs(Page ID) that can be used to retrieve the next and previous result pages.
     * </p>
     * <p>
     * To traverse the entire range, you should keep requesting the next page URLs returned along with result pages until an empty result page is reached.
     * </p>
     *
     * @param domain       Name of the domain
     * @param queryOptions {@link EventsQueryOptions}
     * @return {@link Response}
     */
    @RequestLine("GET /{domain}/events")
    Response getEventsFeignResponse(@Param("domain") String domain, @QueryMap EventsQueryOptions queryOptions);

    /**
     * <p>
     * Fetches the next page of log records, assuming that the <code>pageId</code> was returned by the previous request.
     * </p>
     *
     * @param domain Name of the domain
     * @param pageId Page ID
     * @return {@link EventsResponse}
     */
    @RequestLine("GET /{domain}/events/{pageId}")
    EventsResponse getEvents(@Param("domain") String domain, @Param("pageId") String pageId);

    /**
     * <p>
     * Fetches the next page of log records, assuming that the <code>pageId</code> was returned by the previous request.
     * </p>
     *
     * @param domain Name of the domain
     * @param pageId Page ID
     * @return {@link Response}
     */
    @RequestLine("GET /{domain}/events/{pageId}")
    Response getEventsFeignResponse(@Param("domain") String domain, @Param("pageId") String pageId);

}
