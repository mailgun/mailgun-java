package com.mailgun.api.v3;

import com.mailgun.api.MailgunApi;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.StatisticsOptions;
import com.mailgun.model.tags.TagAggregatesResponse;
import com.mailgun.model.tags.TagCountriesResponse;
import com.mailgun.model.tags.TagDevicesResponse;
import com.mailgun.model.tags.TagLimitsResponse;
import com.mailgun.model.tags.TagProvidersResponse;
import com.mailgun.model.tags.TagStatsQuery;
import com.mailgun.model.tags.TagStatsResult;
import com.mailgun.model.tags.TagUpdateQuery;
import com.mailgun.model.tags.TagUpdateRequest;
import com.mailgun.model.tags.TagsItem;
import com.mailgun.model.tags.TagsQuery;
import com.mailgun.model.tags.TagsResult;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import feign.Response;

/**
 * Tags API (v3) — deprecated in favour of the
 * <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/tags-new">new Tags API</a>.
 * <p>
 * Mailgun lets you tag each outgoing message with a custom value and provides statistics on the tag level.
 * Tags are created on the fly but they are subject to a limit.
 * </p>
 * <p>
 * Note: Unicode characters are not allowed in tags. Any unicode characters found in tags are stripped.
 * If a tag is entirely unicode characters, the tag is ignored.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/tags">Tags</a>
 * @deprecated Use the new Tags API instead.
 */
@Headers("Accept: application/json")
public interface MailgunTagsApi extends MailgunApi {

    /**
     * Returns a list of tags for a domain.
     * Default number of entries to return: 100.
     *
     * @param domain name of the domain
     * @return {@link TagsResult}
     * @deprecated Use the new Tags API instead.
     */
    @RequestLine("GET /{domain}/tags")
    TagsResult getAllTags(@Param("domain") String domain);

    /**
     * Returns a list of tags for a domain.
     *
     * @param domain name of the domain
     * @return {@link Response}
     * @deprecated Use the new Tags API instead.
     */
    @RequestLine("GET /{domain}/tags")
    Response getAllTagsFeignResponse(@Param("domain") String domain);

    /**
     * Returns a list of tags for a domain, limited to the given count.
     *
     * @param domain name of the domain
     * @param limit  number of entries to return
     * @return {@link TagsResult}
     * @deprecated Use {@link #getAllTags(String, TagsQuery)} for full pagination support.
     */
    @RequestLine("GET /{domain}/tags?limit={limit}")
    TagsResult getAllTags(@Param("domain") String domain, @Param("limit") Integer limit);

    /**
     * Returns a list of tags for a domain, limited to the given count.
     *
     * @param domain name of the domain
     * @param limit  number of entries to return
     * @return {@link Response}
     * @deprecated Use {@link #getAllTagsFeignResponse(String, TagsQuery)} for full pagination support.
     */
    @RequestLine("GET /{domain}/tags?limit={limit}")
    Response getAllTagsFeignResponse(@Param("domain") String domain, @Param("limit") Integer limit);

    /**
     * Returns a paginated list of tags for a domain with optional prefix filter.
     *
     * @param domain name of the domain
     * @param query  {@link TagsQuery} — page direction, limit, cursor tag, and prefix filter
     * @return {@link TagsResult}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/tags/get-v3--domain--tags.md">List all tags</a>
     * @deprecated Use the new Tags API instead.
     */
    @RequestLine("GET /{domain}/tags")
    TagsResult getAllTags(@Param("domain") String domain, @QueryMap TagsQuery query);

    /**
     * Returns a paginated list of tags for a domain with optional prefix filter (raw response).
     *
     * @param domain name of the domain
     * @param query  {@link TagsQuery}
     * @return {@link Response}
     * @deprecated Use the new Tags API instead.
     */
    @RequestLine("GET /{domain}/tags")
    Response getAllTagsFeignResponse(@Param("domain") String domain, @QueryMap TagsQuery query);

    /**
     * Returns a given tag by path parameter.
     *
     * @param domain name of the domain
     * @param tag    name of the tag
     * @return {@link TagsItem}
     * @deprecated Use the new Tags API instead.
     */
    @RequestLine("GET /{domain}/tags/{tag}")
    TagsItem getTag(@Param("domain") String domain, @Param("tag") String tag);

    /**
     * Returns a given tag by path parameter (raw response).
     *
     * @param domain name of the domain
     * @param tag    name of the tag
     * @return {@link Response}
     * @deprecated Use the new Tags API instead.
     */
    @RequestLine("GET /{domain}/tags/{tag}")
    Response getTagFeignResponse(@Param("domain") String domain, @Param("tag") String tag);

    /**
     * Returns a tag by query parameter.
     *
     * @param domain name of the domain
     * @param tag    name of the tag (query parameter)
     * @return {@link TagsItem}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/tags/get-v3--domain--tag.md">Get a tag</a>
     * @deprecated Use the new Tags API instead.
     */
    @RequestLine("GET /{domain}/tag?tag={tag}")
    TagsItem getTagByQuery(@Param("domain") String domain, @Param("tag") String tag);

    /**
     * Returns a tag by query parameter (raw response).
     *
     * @param domain name of the domain
     * @param tag    name of the tag (query parameter)
     * @return {@link Response}
     * @deprecated Use the new Tags API instead.
     */
    @RequestLine("GET /{domain}/tag?tag={tag}")
    Response getTagByQueryFeignResponse(@Param("domain") String domain, @Param("tag") String tag);

    /**
     * Updates a given tag with the information provided (path parameter form).
     *
     * @param domain           name of the domain
     * @param tag              name of the tag
     * @param tagUpdateRequest {@link TagUpdateRequest}
     * @return {@link ResponseWithMessage}
     * @deprecated Use the new Tags API instead.
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /{domain}/tags/{tag}")
    ResponseWithMessage updateTag(@Param("domain") String domain, @Param("tag") String tag, TagUpdateRequest tagUpdateRequest);

    /**
     * Updates a given tag with the information provided (path parameter form, raw response).
     *
     * @param domain           name of the domain
     * @param tag              name of the tag
     * @param tagUpdateRequest {@link TagUpdateRequest}
     * @return {@link Response}
     * @deprecated Use the new Tags API instead.
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /{domain}/tags/{tag}")
    Response updateTagFeignResponse(@Param("domain") String domain, @Param("tag") String tag, TagUpdateRequest tagUpdateRequest);

    /**
     * Updates a tag description via query parameters.
     *
     * @param domain name of the domain
     * @param query  {@link TagUpdateQuery} — tag name (required) and description (optional)
     * @return {@link ResponseWithMessage}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/tags/put-v3--domain--tag.md">Update tag</a>
     * @deprecated Use the new Tags API instead.
     */
    @RequestLine("PUT /{domain}/tag")
    ResponseWithMessage updateTagByQuery(@Param("domain") String domain, @QueryMap TagUpdateQuery query);

    /**
     * Updates a tag description via query parameters (raw response).
     *
     * @param domain name of the domain
     * @param query  {@link TagUpdateQuery}
     * @return {@link Response}
     * @deprecated Use the new Tags API instead.
     */
    @RequestLine("PUT /{domain}/tag")
    Response updateTagByQueryFeignResponse(@Param("domain") String domain, @QueryMap TagUpdateQuery query);

    /**
     * Returns statistics for a given tag (path parameter form).
     *
     * @param domain            name of the domain
     * @param tag               name of the tag
     * @param statisticsOptions {@link StatisticsOptions}
     * @return {@link TagStatsResult}
     * @deprecated Use the new Tags API instead.
     */
    @RequestLine("GET /{domain}/tags/{tag}/stats")
    TagStatsResult getTagStatistics(@Param("domain") String domain, @Param("tag") String tag, @QueryMap StatisticsOptions statisticsOptions);

    /**
     * Returns statistics for a given tag (path parameter form, raw response).
     *
     * @param domain            name of the domain
     * @param tag               name of the tag
     * @param statisticsOptions {@link StatisticsOptions}
     * @return {@link Response}
     * @deprecated Use the new Tags API instead.
     */
    @RequestLine("GET /{domain}/tags/{tag}/stats")
    Response getTagStatisticsFeignResponse(@Param("domain") String domain, @Param("tag") String tag, @QueryMap StatisticsOptions statisticsOptions);

    /**
     * Returns statistics for a given tag via query parameters.
     * Supports additional filters: provider, device, country.
     *
     * @param domain name of the domain
     * @param query  {@link TagStatsQuery} — tag and event are required
     * @return {@link TagStatsResult}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/tags/get-v3--domain--tag-stats.md">Get stats by tag</a>
     * @deprecated Use the new Tags API instead.
     */
    @RequestLine("GET /{domain}/tag/stats")
    TagStatsResult getTagStatsByQuery(@Param("domain") String domain, @QueryMap TagStatsQuery query);

    /**
     * Returns statistics for a given tag via query parameters (raw response).
     *
     * @param domain name of the domain
     * @param query  {@link TagStatsQuery}
     * @return {@link Response}
     * @deprecated Use the new Tags API instead.
     */
    @RequestLine("GET /{domain}/tag/stats")
    Response getTagStatsByQueryFeignResponse(@Param("domain") String domain, @QueryMap TagStatsQuery query);

    /**
     * Deletes a tag by path parameter.
     * Note: The statistics for the tag are not destroyed.
     *
     * @param domain name of the domain
     * @param tag    name of the tag
     * @return {@link ResponseWithMessage}
     * @deprecated Use the new Tags API instead.
     */
    @RequestLine("DELETE /{domain}/tags/{tag}")
    ResponseWithMessage deleteTag(@Param("domain") String domain, @Param("tag") String tag);

    /**
     * Deletes a tag by path parameter (raw response).
     *
     * @param domain name of the domain
     * @param tag    name of the tag
     * @return {@link Response}
     * @deprecated Use the new Tags API instead.
     */
    @RequestLine("DELETE /{domain}/tags/{tag}")
    Response deleteTagFeignResponse(@Param("domain") String domain, @Param("tag") String tag);

    /**
     * Deletes a tag via query parameter.
     * Note: The statistics for the tag are not destroyed.
     *
     * @param domain name of the domain
     * @param tag    name of the tag (query parameter)
     * @return {@link ResponseWithMessage}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/tags/delete-v3--domain--tag.md">Delete tag</a>
     * @deprecated Use the new Tags API instead.
     */
    @RequestLine("DELETE /{domain}/tag?tag={tag}")
    ResponseWithMessage deleteTagByQuery(@Param("domain") String domain, @Param("tag") String tag);

    /**
     * Deletes a tag via query parameter (raw response).
     *
     * @param domain name of the domain
     * @param tag    name of the tag (query parameter)
     * @return {@link Response}
     * @deprecated Use the new Tags API instead.
     */
    @RequestLine("DELETE /{domain}/tag?tag={tag}")
    Response deleteTagByQueryFeignResponse(@Param("domain") String domain, @Param("tag") String tag);

    /**
     * Returns aggregate stat counts (provider, country, device) for a tag.
     *
     * @param domain name of the domain
     * @param tag    name of the tag
     * @param type   aggregate type: country, device, or provider
     * @return {@link TagAggregatesResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/tags/get-v3--domain--tag-stats-aggregates.md">Get aggregate stat types by tag</a>
     * @deprecated Use the new Tags API instead.
     */
    @RequestLine("GET /{domain}/tag/stats/aggregates?tag={tag}&type={type}")
    TagAggregatesResponse getTagAggregates(@Param("domain") String domain, @Param("tag") String tag, @Param("type") String type);

    /**
     * Returns aggregate stat counts for a tag (raw response).
     *
     * @param domain name of the domain
     * @param tag    name of the tag
     * @param type   aggregate type: country, device, or provider
     * @return {@link Response}
     * @deprecated Use the new Tags API instead.
     */
    @RequestLine("GET /{domain}/tag/stats/aggregates?tag={tag}&type={type}")
    Response getTagAggregatesFeignResponse(@Param("domain") String domain, @Param("tag") String tag, @Param("type") String type);

    /**
     * Returns a list of countries of origin for a tag.
     *
     * @param domain name of the domain
     * @param tag    name of the tag
     * @return {@link TagCountriesResponse}
     * @deprecated Use the new Tags API instead.
     */
    @RequestLine("GET /{domain}/tags/{tag}/stats/aggregates/countries")
    TagCountriesResponse listTagCountries(@Param("domain") String domain, @Param("tag") String tag);

    /**
     * Returns a list of countries of origin for a tag (raw response).
     *
     * @param domain name of the domain
     * @param tag    name of the tag
     * @return {@link Response}
     * @deprecated Use the new Tags API instead.
     */
    @RequestLine("GET /{domain}/tags/{tag}/stats/aggregates/countries")
    Response listTagCountriesFeignResponse(@Param("domain") String domain, @Param("tag") String tag);

    /**
     * Returns a list of email providers for a tag.
     *
     * @param domain name of the domain
     * @param tag    name of the tag
     * @return {@link TagProvidersResponse}
     * @deprecated Use the new Tags API instead.
     */
    @RequestLine("GET /{domain}/tags/{tag}/stats/aggregates/providers")
    TagProvidersResponse listTagProviders(@Param("domain") String domain, @Param("tag") String tag);

    /**
     * Returns a list of email providers for a tag (raw response).
     *
     * @param domain name of the domain
     * @param tag    name of the tag
     * @return {@link Response}
     * @deprecated Use the new Tags API instead.
     */
    @RequestLine("GET /{domain}/tags/{tag}/stats/aggregates/providers")
    Response listTagProvidersFeignResponse(@Param("domain") String domain, @Param("tag") String tag);

    /**
     * Returns a list of devices for a tag.
     *
     * @param domain name of the domain
     * @param tag    name of the tag
     * @return {@link TagDevicesResponse}
     * @deprecated Use the new Tags API instead.
     */
    @RequestLine("GET /{domain}/tags/{tag}/stats/aggregates/devices")
    TagDevicesResponse listTagDevices(@Param("domain") String domain, @Param("tag") String tag);

    /**
     * Returns a list of devices for a tag (raw response).
     *
     * @param domain name of the domain
     * @param tag    name of the tag
     * @return {@link Response}
     * @deprecated Use the new Tags API instead.
     */
    @RequestLine("GET /{domain}/tags/{tag}/stats/aggregates/devices")
    Response listTagDevicesFeignResponse(@Param("domain") String domain, @Param("tag") String tag);

    /**
     * Gets the tag limit and current tag count for a domain.
     *
     * @param domain name of the domain
     * @return {@link TagLimitsResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/tags/get-v3-domains--domain--limits-tag.md">Get tag limits</a>
     * @deprecated Use the new Tags API instead.
     */
    @RequestLine("GET /domains/{domain}/limits/tag")
    TagLimitsResponse getTagLimits(@Param("domain") String domain);

    /**
     * Gets the tag limit and current tag count for a domain (raw response).
     *
     * @param domain name of the domain
     * @return {@link Response}
     * @deprecated Use the new Tags API instead.
     */
    @RequestLine("GET /domains/{domain}/limits/tag")
    Response getTagLimitsFeignResponse(@Param("domain") String domain);

}
