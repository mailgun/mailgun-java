package com.mailgun.api.v3;

import com.mailgun.api.MailgunApi;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.StatisticsOptions;
import com.mailgun.model.tags.TagCountriesResponse;
import com.mailgun.model.tags.TagDevicesResponse;
import com.mailgun.model.tags.TagProvidersResponse;
import com.mailgun.model.tags.TagStatsResult;
import com.mailgun.model.tags.TagUpdateRequest;
import com.mailgun.model.tags.TagsItem;
import com.mailgun.model.tags.TagsResult;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import feign.Response;

/**
 * <p>
 * The tags API
 * </p>
 * <p>
 * Mailgun lets you tag each outgoing message with a custom value and provides statistics on the tag level.
 * </p>
 * <p>
 * Tags are created on the fly but they are subject to a limit.
 * </p>
 *
 * <p>
 * Note: Unicode characters are not allowed in tags. Any unicode characters found in tags are stripped from the tag.
 * If tag is entirely unicode characters, the tag is ignored.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-tags.html">Tags</a>
 */
@Headers("Accept: application/json")
public interface MailgunTagsApi extends MailgunApi {

    /**
     * <p>
     * Returns a list of tags for a domain.
     * </p>
     * Default number of entries to return: 100.
     *
     * @param domain Name of the domain
     * @return {@link TagsResult}
     */
    @RequestLine("GET /{domain}/tags")
    TagsResult getAllTags(@Param("domain") String domain);

    /**
     * <p>
     * Returns a list of tags for a domain.
     * </p>
     * Default number of entries to return: 100.
     *
     * @param domain Name of the domain
     * @return {@link Response}
     */
    @RequestLine("GET /{domain}/tags")
    Response getAllTagsFeignResponse(@Param("domain") String domain);

    /**
     * <p>
     * Returns a list of tags for a domain.
     * </p>
     *
     * @param domain Name of the domain
     * @param limit  Number of entries to return.
     * @return {@link TagsResult}
     */
    @RequestLine("GET /{domain}/tags?limit={limit}")
    TagsResult getAllTags(@Param("domain") String domain, @Param("limit") Integer limit);

    /**
     * <p>
     * Returns a list of tags for a domain.
     * </p>
     *
     * @param domain Name of the domain
     * @param limit  Number of entries to return.
     * @return {@link Response}
     */
    @RequestLine("GET /{domain}/tags?limit={limit}")
    Response getAllTagsFeignResponse(@Param("domain") String domain, @Param("limit") Integer limit);

    /**
     * <p>
     * Returns a given tag.
     * </p>
     *
     * @param domain Name of the domain
     * @param tag    Name of the tag
     * @return {@link TagsItem}
     */
    @RequestLine("GET /{domain}/tags/{tag}")
    TagsItem getTag(@Param("domain") String domain, @Param("tag") String tag);

    /**
     * <p>
     * Returns a given tag.
     * </p>
     *
     * @param domain Name of the domain
     * @param tag    Name of the tag
     * @return {@link Response}
     */
    @RequestLine("GET /{domain}/tags/{tag}")
    Response getTagFeignResponse(@Param("domain") String domain, @Param("tag") String tag);

    /**
     * <p>
     * Updates a given tag with the information provided.
     * </p>
     *
     * @param domain           Name of the domain
     * @param tag              Name of the tag
     * @param tagUpdateRequest {@link TagUpdateRequest}
     * @return {@link ResponseWithMessage}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /{domain}/tags/{tag}")
    ResponseWithMessage updateTag(@Param("domain") String domain, @Param("tag") String tag, TagUpdateRequest tagUpdateRequest);

    /**
     * <p>
     * Updates a given tag with the information provided.
     * </p>
     *
     * @param domain           Name of the domain
     * @param tag              Name of the tag
     * @param tagUpdateRequest {@link TagUpdateRequest}
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /{domain}/tags/{tag}")
    Response updateTagFeignResponse(@Param("domain") String domain, @Param("tag") String tag, TagUpdateRequest tagUpdateRequest);

    /**
     * <p>
     * Returns statistics for a given tag.
     * </p>
     *
     * @param domain            Name of the domain
     * @param tag               Name of the tag
     * @param statisticsOptions {@link StatisticsOptions}
     * @return {@link TagStatsResult}
     */
    @RequestLine("GET /{domain}/tags/{tag}/stats")
    TagStatsResult getTagStatistics(@Param("domain") String domain, @Param("tag") String tag, @QueryMap StatisticsOptions statisticsOptions);

    /**
     * <p>
     * Returns statistics for a given tag.
     * </p>
     *
     * @param domain            Name of the domain
     * @param tag               Name of the tag
     * @param statisticsOptions {@link StatisticsOptions}
     * @return {@link Response}
     */
    @RequestLine("GET /{domain}/tags/{tag}/stats")
    Response getTagStatisticsFeignResponse(@Param("domain") String domain, @Param("tag") String tag, @QueryMap StatisticsOptions statisticsOptions);

    /**
     * <p>
     * Deletes the tag.
     * </p>
     * Note: The statistics for the tag are not destroyed.
     *
     * @param domain Name of the domain
     * @param tag    Name of the tag
     * @return {@link ResponseWithMessage}
     */
    @RequestLine("DELETE /{domain}/tags/{tag}")
    ResponseWithMessage deleteTag(@Param("domain") String domain, @Param("tag") String tag);

    /**
     * <p>
     * Deletes the tag.
     * </p>
     * Note: The statistics for the tag are not destroyed.
     *
     * @param domain Name of the domain
     * @param tag    Name of the tag
     * @return {@link Response}
     */
    @RequestLine("DELETE /{domain}/tags/{tag}")
    Response deleteTagFeignResponse(@Param("domain") String domain, @Param("tag") String tag);

    /**
     * <p>
     * Returns a list of countries of origin for a given domain for different event types.
     * </p>
     *
     * @param domain Name of the domain
     * @param tag    Name of the tag
     * @return {@link TagCountriesResponse}
     */
    @RequestLine("GET /{domain}/tags/{tag}/stats/aggregates/countries")
    TagCountriesResponse listTagCountries(@Param("domain") String domain, @Param("tag") String tag);

    /**
     * <p>
     * Returns a list of countries of origin for a given domain for different event types.
     * </p>
     *
     * @param domain Name of the domain
     * @param tag    Name of the tag
     * @return {@link Response}
     */
    @RequestLine("GET /{domain}/tags/{tag}/stats/aggregates/countries")
    Response listTagCountriesFeignResponse(@Param("domain") String domain, @Param("tag") String tag);

    /**
     * <p>
     * Returns a list of email providers for a given domain for different event types.
     * </p>
     *
     * @param domain Name of the domain
     * @param tag    Name of the tag
     * @return {@link TagProvidersResponse}
     */
    @RequestLine("GET /{domain}/tags/{tag}/stats/aggregates/providers")
    TagProvidersResponse listTagProviders(@Param("domain") String domain, @Param("tag") String tag);

    /**
     * <p>
     * Returns a list of email providers for a given domain for different event types.
     * </p>
     *
     * @param domain Name of the domain
     * @param tag    Name of the tag
     * @return {@link Response}
     */
    @RequestLine("GET /{domain}/tags/{tag}/stats/aggregates/providers")
    Response listTagProvidersFeignResponse(@Param("domain") String domain, @Param("tag") String tag);

    /**
     * <p>
     * Returns a list of devices for a given domain that have triggered event types.
     * </p>
     *
     * @param domain Name of the domain
     * @param tag    Name of the tag
     * @return {@link TagDevicesResponse}
     */
    @RequestLine("GET /{domain}/tags/{tag}/stats/aggregates/devices")
    TagDevicesResponse listTagDevices(@Param("domain") String domain, @Param("tag") String tag);

    /**
     * <p>
     * Returns a list of devices for a given domain that have triggered event types.
     * </p>
     *
     * @param domain Name of the domain
     * @param tag    Name of the tag
     * @return {@link Response}
     */
    @RequestLine("GET /{domain}/tags/{tag}/stats/aggregates/devices")
    Response listTagDevicesFeignResponse(@Param("domain") String domain, @Param("tag") String tag);

}
