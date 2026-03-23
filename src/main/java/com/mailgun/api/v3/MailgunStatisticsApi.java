package com.mailgun.api.v3;

import com.mailgun.api.MailgunApi;
import com.mailgun.model.StatisticsOptions;
import com.mailgun.model.stats.StatsAggregateCountriesResponse;
import com.mailgun.model.stats.StatsAggregateDevicesResponse;
import com.mailgun.model.stats.StatsAggregateProvidersResponse;
import com.mailgun.model.stats.StatsResult;
import com.mailgun.model.stats.StatsTotalDomainsOptions;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import feign.Response;

/**
 * Stats API (v3) — deprecated in favour of the
 * <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/metrics">Metrics API</a>.
 * <p>
 * Mailgun collects many different events and generates event statistics available via this API.
 * The statistics are calculated in hourly, daily and monthly resolution in UTC timezone.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/stats">Stats</a>
 * @deprecated Use the Metrics API instead.
 */
@Headers("Accept: application/json")
public interface MailgunStatisticsApi extends MailgunApi {

    /**
     * Gets stat totals for an entire account.
     *
     * @param statisticsOptions {@link StatisticsOptions} — {@code event} is required
     * @return {@link StatsResult}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/stats/get-v3-stats-total.md">Totals for entire account</a>
     * @deprecated Use the Metrics API instead.
     */
    @RequestLine("GET /stats/total")
    StatsResult getAccountStats(@QueryMap StatisticsOptions statisticsOptions);

    /**
     * Gets stat totals for an entire account (raw response).
     *
     * @param statisticsOptions {@link StatisticsOptions} — {@code event} is required
     * @return {@link Response}
     * @deprecated Use the Metrics API instead.
     */
    @RequestLine("GET /stats/total")
    Response getAccountStatsFeignResponse(@QueryMap StatisticsOptions statisticsOptions);

    /**
     * Gets stat totals for an entire domain.
     *
     * @param domain            name of the domain
     * @param statisticsOptions {@link StatisticsOptions} — {@code event} is required
     * @return {@link StatsResult}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/stats/get-v3--domain--stats-total.md">Totals for entire domain</a>
     * @deprecated Use the Metrics API instead.
     */
    @RequestLine("GET /{domain}/stats/total")
    StatsResult getDomainStats(@Param("domain") String domain, @QueryMap StatisticsOptions statisticsOptions);

    /**
     * Gets stat totals for an entire domain (raw response).
     *
     * @param domain            name of the domain
     * @param statisticsOptions {@link StatisticsOptions} — {@code event} is required
     * @return {@link Response}
     * @deprecated Use the Metrics API instead.
     */
    @RequestLine("GET /{domain}/stats/total")
    Response getDomainStatsFeignResponse(@Param("domain") String domain, @QueryMap StatisticsOptions statisticsOptions);

    /**
     * Gets stat totals for all domains in an account for a single time resolution.
     *
     * @param options {@link StatsTotalDomainsOptions} — {@code event} and {@code timestamp} are required
     * @return {@link StatsResult}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/stats/get-v3-stats-total-domains.md">Totals for account domains for a single time resolution</a>
     * @deprecated Use the Metrics API instead.
     */
    @RequestLine("GET /stats/total/domains")
    StatsResult getAccountStatsByDomains(@QueryMap StatsTotalDomainsOptions options);

    /**
     * Gets stat totals for all domains in an account for a single time resolution (raw response).
     *
     * @param options {@link StatsTotalDomainsOptions} — {@code event} and {@code timestamp} are required
     * @return {@link Response}
     * @deprecated Use the Metrics API instead.
     */
    @RequestLine("GET /stats/total/domains")
    Response getAccountStatsByDomainsFeignResponse(@QueryMap StatsTotalDomainsOptions options);

    /**
     * Gets filtered and grouped stat totals for an entire account.
     * Set {@code filter} and/or {@code group} on the {@link StatisticsOptions} to apply filtering/grouping.
     *
     * @param statisticsOptions {@link StatisticsOptions} — {@code event} is required;
     *                          optionally set {@code filter} (e.g. "domain:my.example.com") and {@code group}
     *                          (one of: total, time, day, month, domain, ip, provider, tag, country)
     * @return {@link StatsResult}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/stats/get-v3-stats-filter.md">Filtered/grouped totals for entire account</a>
     * @deprecated Use the Metrics API instead.
     */
    @RequestLine("GET /stats/filter")
    StatsResult getFilteredAccountStats(@QueryMap StatisticsOptions statisticsOptions);

    /**
     * Gets filtered and grouped stat totals for an entire account (raw response).
     *
     * @param statisticsOptions {@link StatisticsOptions} — {@code event} is required
     * @return {@link Response}
     * @deprecated Use the Metrics API instead.
     */
    @RequestLine("GET /stats/filter")
    Response getFilteredAccountStatsFeignResponse(@QueryMap StatisticsOptions statisticsOptions);

    /**
     * Gets aggregate counts by email service provider for a domain.
     *
     * @param domain name of the domain
     * @return {@link StatsAggregateProvidersResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/stats/get-v3--domain--aggregates-providers.md">Aggregate counts by ESP</a>
     * @deprecated Use the Metrics API instead.
     */
    @RequestLine("GET /{domain}/aggregates/providers")
    StatsAggregateProvidersResponse getAggregateProviders(@Param("domain") String domain);

    /**
     * Gets aggregate counts by email service provider for a domain (raw response).
     *
     * @param domain name of the domain
     * @return {@link Response}
     * @deprecated Use the Metrics API instead.
     */
    @RequestLine("GET /{domain}/aggregates/providers")
    Response getAggregateProvidersFeignResponse(@Param("domain") String domain);

    /**
     * Gets aggregate counts by device type for a domain.
     *
     * @param domain name of the domain
     * @return {@link StatsAggregateDevicesResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/stats/get-v3--domain--aggregates-devices.md">Aggregate counts by devices triggering events</a>
     * @deprecated Use the Metrics API instead.
     */
    @RequestLine("GET /{domain}/aggregates/devices")
    StatsAggregateDevicesResponse getAggregateDevices(@Param("domain") String domain);

    /**
     * Gets aggregate counts by device type for a domain (raw response).
     *
     * @param domain name of the domain
     * @return {@link Response}
     * @deprecated Use the Metrics API instead.
     */
    @RequestLine("GET /{domain}/aggregates/devices")
    Response getAggregateDevicesFeignResponse(@Param("domain") String domain);

    /**
     * Gets aggregate counts by country for a domain.
     *
     * @param domain name of the domain
     * @return {@link StatsAggregateCountriesResponse}
     * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/stats/get-v3--domain--aggregates-countries.md">Aggregate counts by country</a>
     * @deprecated Use the Metrics API instead.
     */
    @RequestLine("GET /{domain}/aggregates/countries")
    StatsAggregateCountriesResponse getAggregateCountries(@Param("domain") String domain);

    /**
     * Gets aggregate counts by country for a domain (raw response).
     *
     * @param domain name of the domain
     * @return {@link Response}
     * @deprecated Use the Metrics API instead.
     */
    @RequestLine("GET /{domain}/aggregates/countries")
    Response getAggregateCountriesFeignResponse(@Param("domain") String domain);

}
