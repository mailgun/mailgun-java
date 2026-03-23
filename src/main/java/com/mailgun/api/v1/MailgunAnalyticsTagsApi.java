package com.mailgun.api.v1;

import com.mailgun.api.MailgunApi;
import com.mailgun.enums.ApiVersion;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.tags.AnalyticsTagDeleteRequest;
import com.mailgun.model.tags.AnalyticsTagLimitsResponse;
import com.mailgun.model.tags.AnalyticsTagListRequest;
import com.mailgun.model.tags.AnalyticsTagListResponse;
import com.mailgun.model.tags.AnalyticsTagUpdateRequest;
import feign.Headers;
import feign.RequestLine;
import feign.Response;

/**
 * Account-level analytics tags API (v1).
 * Allows listing, updating, deleting, and querying limits for tags across an account.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/tags-new/">Tags New</a>
 */
@Headers({"Accept: application/json", "Content-Type: application/json"})
public interface MailgunAnalyticsTagsApi extends MailgunApi {

    static ApiVersion getApiVersion() {
        return ApiVersion.V_1;
    }

    /**
     * Gets the list of all tags, or filtered by tag prefix, for an account.
     *
     * @param request {@link AnalyticsTagListRequest}
     * @return {@link AnalyticsTagListResponse}
     */
    @RequestLine("POST /analytics/tags")
    AnalyticsTagListResponse listTags(AnalyticsTagListRequest request);

    /**
     * Gets the list of all tags, or filtered by tag prefix, for an account.
     *
     * @param request {@link AnalyticsTagListRequest}
     * @return {@link Response}
     */
    @RequestLine("POST /analytics/tags")
    Response listTagsFeignResponse(AnalyticsTagListRequest request);

    /**
     * Updates the tag description for an account.
     *
     * @param request {@link AnalyticsTagUpdateRequest}
     * @return {@link ResponseWithMessage}
     */
    @RequestLine("PUT /analytics/tags")
    ResponseWithMessage updateTag(AnalyticsTagUpdateRequest request);

    /**
     * Updates the tag description for an account.
     *
     * @param request {@link AnalyticsTagUpdateRequest}
     * @return {@link Response}
     */
    @RequestLine("PUT /analytics/tags")
    Response updateTagFeignResponse(AnalyticsTagUpdateRequest request);

    /**
     * Deletes the tag for an account.
     *
     * @param request {@link AnalyticsTagDeleteRequest}
     * @return {@link ResponseWithMessage}
     */
    @RequestLine("DELETE /analytics/tags")
    ResponseWithMessage deleteTag(AnalyticsTagDeleteRequest request);

    /**
     * Deletes the tag for an account.
     *
     * @param request {@link AnalyticsTagDeleteRequest}
     * @return {@link Response}
     */
    @RequestLine("DELETE /analytics/tags")
    Response deleteTagFeignResponse(AnalyticsTagDeleteRequest request);

    /**
     * Gets the tag limit and current number of unique tags for an account.
     *
     * @return {@link AnalyticsTagLimitsResponse}
     */
    @RequestLine("GET /analytics/tags/limits")
    AnalyticsTagLimitsResponse getTagLimits();

    /**
     * Gets the tag limit and current number of unique tags for an account.
     *
     * @return {@link Response}
     */
    @RequestLine("GET /analytics/tags/limits")
    Response getTagLimitsFeignResponse();

}
