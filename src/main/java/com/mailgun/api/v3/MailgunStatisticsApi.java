package com.mailgun.api.v3;

import com.mailgun.api.MailgunApi;
import com.mailgun.model.StatisticsOptions;
import com.mailgun.model.stats.StatsResult;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import feign.Response;

/**
 * <p>
 * Mailgun collects many different events and generates event statistics available via this API.
 * </p>
 * <p>
 * This data is also available in your Control Panel.
 * </p>
 * <p>
 * The statistics are calculated in hourly, daily and monthly resolution in UTC timezone.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-stats.html">Stats</a>
 */
@Headers({"Accept: application/json"})
public interface MailgunStatisticsApi extends MailgunApi {

    /**
     * <p>
     * Returns total statistics for a given domain.
     * </p>
     *
     * @param domain            Name of the domain
     * @param statisticsOptions {@link StatisticsOptions}
     * @return {@link StatsResult}
     */
    @RequestLine("GET /{domain}/stats/total")
    StatsResult getDomainStats(@Param("domain") String domain, @QueryMap StatisticsOptions statisticsOptions);

    /**
     * <p>
     * Returns total statistics for a given domain.
     * </p>
     *
     * @param domain            Name of the domain
     * @param statisticsOptions {@link StatisticsOptions}
     * @return {@link Response}
     */
    @RequestLine("GET /{domain}/stats/total")
    Response getDomainStatsFeignResponse(@Param("domain") String domain, @QueryMap StatisticsOptions statisticsOptions);

}
