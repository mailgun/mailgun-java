package com.mailgun.api.v3;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.mailgun.api.MailgunApi;
import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.enums.Duration;
import com.mailgun.enums.ResolutionPeriod;
import com.mailgun.enums.StatsEventType;
import com.mailgun.model.StatisticsOptions;
import com.mailgun.model.stats.Stats;
import com.mailgun.model.stats.StatsAggregateCountriesResponse;
import com.mailgun.model.stats.StatsAggregateDevicesResponse;
import com.mailgun.model.stats.StatsAggregateProvidersResponse;
import com.mailgun.model.stats.StatsResult;
import com.mailgun.model.stats.StatsTotalDomainsOptions;
import com.mailgun.model.stats.StatsTotalValueObject;
import com.mailgun.util.StringUtil;
import com.mailgun.utils.TestHeadersUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.mailgun.constants.TestConstants.TEST_API_KEY;
import static com.mailgun.constants.TestConstants.TEST_DOMAIN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MailgunStatisticsApiTest extends WireMockBaseTest {

    private static final String API_BASE = "/" + MailgunApi.getApiVersion().getValue();

    private MailgunStatisticsApi mailgunStatisticsApi;

    @BeforeEach
    void setUp() {
        mailgunStatisticsApi = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunStatisticsApi.class);
    }

    @Test
    void getStatisticsTotalSuccessTest() {
        Stats stats = Stats.builder()
                .stored(StatsTotalValueObject.builder().total(5).build())
                .opened(StatsTotalValueObject.builder().total(5).build())
                .clicked(StatsTotalValueObject.builder().total(5).build())
                .unsubscribed(StatsTotalValueObject.builder().total(5).build())
                .complained(StatsTotalValueObject.builder().total(5).build())
                .build();

        StatsResult statsResult = StatsResult.builder()
                .description("Some description")
                .resolution(ResolutionPeriod.DAY)
                .stats(Collections.singletonList(stats))
                .build();

        stubFor(get(urlPathEqualTo(API_BASE + "/" + TEST_DOMAIN + "/stats/total"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withQueryParam("resolution", WireMock.equalTo("day"))
                .withQueryParam("duration", WireMock.equalTo("3d"))
                .withQueryParam("event", WireMock.equalTo("accepted"))
                .withQueryParam("event", WireMock.equalTo("delivered"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(StringUtil.asJsonString(statsResult))));

        StatisticsOptions statsOptions = StatisticsOptions.builder()
                .event(Arrays.asList(StatsEventType.ACCEPTED, StatsEventType.DELIVERED))
                .resolution(ResolutionPeriod.DAY)
                .duration(3, Duration.DAY)
                .build();

        StatsResult result = mailgunStatisticsApi.getDomainStats(TEST_DOMAIN, statsOptions);

        assertNotNull(result);
        assertEquals(statsResult, result);
    }

    @Test
    void getAccountStatsTotalTest() {
        StatsResult expected = StatsResult.builder()
                .description("Account totals")
                .resolution(ResolutionPeriod.DAY)
                .stats(Collections.emptyList())
                .build();

        stubFor(get(urlPathEqualTo(API_BASE + "/stats/total"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withQueryParam("event", WireMock.equalTo("delivered"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(StringUtil.asJsonString(expected))));

        StatisticsOptions options = StatisticsOptions.builder()
                .event(StatsEventType.DELIVERED)
                .build();

        StatsResult result = mailgunStatisticsApi.getAccountStats(options);

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void getAccountStatsByDomainsTest() {
        StatsResult expected = StatsResult.builder()
                .description("Domain breakdown")
                .resolution(ResolutionPeriod.HOUR)
                .stats(Collections.emptyList())
                .build();

        String timestamp = "Mon, 01 Jan 2024 00:00:00 +0000";

        stubFor(get(urlPathEqualTo(API_BASE + "/stats/total/domains"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withQueryParam("event", WireMock.equalTo("opened"))
                .withQueryParam("resolution", WireMock.equalTo("hour"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(StringUtil.asJsonString(expected))));

        StatsTotalDomainsOptions options = StatsTotalDomainsOptions.builder()
                .event(StatsEventType.OPENED)
                .resolution(ResolutionPeriod.HOUR)
                .timestamp(timestamp)
                .build();

        StatsResult result = mailgunStatisticsApi.getAccountStatsByDomains(options);

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void getFilteredAccountStatsTest() {
        StatsResult expected = StatsResult.builder()
                .description("Filtered totals")
                .resolution(ResolutionPeriod.DAY)
                .stats(Collections.emptyList())
                .build();

        stubFor(get(urlPathEqualTo(API_BASE + "/stats/filter"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withQueryParam("event", WireMock.equalTo("clicked"))
                .withQueryParam("filter", WireMock.equalTo("domain:" + TEST_DOMAIN))
                .withQueryParam("group", WireMock.equalTo("domain"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(StringUtil.asJsonString(expected))));

        StatisticsOptions options = StatisticsOptions.builder()
                .event(StatsEventType.CLICKED)
                .filter("domain:" + TEST_DOMAIN)
                .group("domain")
                .build();

        StatsResult result = mailgunStatisticsApi.getFilteredAccountStats(options);

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void getAggregateProvidersTest() {
        Map<String, Object> providers = new HashMap<>();
        providers.put("Gmail", Collections.singletonMap("opened", 42));
        StatsAggregateProvidersResponse expected = StatsAggregateProvidersResponse.builder()
                .providers(providers)
                .build();

        stubFor(get(urlPathEqualTo(API_BASE + "/" + TEST_DOMAIN + "/aggregates/providers"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(StringUtil.asJsonString(expected))));

        StatsAggregateProvidersResponse result = mailgunStatisticsApi.getAggregateProviders(TEST_DOMAIN);

        assertNotNull(result);
        assertNotNull(result.getProviders());
        assertEquals(1, result.getProviders().size());
    }

    @Test
    void getAggregateDevicesTest() {
        Map<String, Object> devices = new HashMap<>();
        devices.put("desktop", Collections.singletonMap("opened", 10));
        StatsAggregateDevicesResponse expected = StatsAggregateDevicesResponse.builder()
                .devices(devices)
                .build();

        stubFor(get(urlPathEqualTo(API_BASE + "/" + TEST_DOMAIN + "/aggregates/devices"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(StringUtil.asJsonString(expected))));

        StatsAggregateDevicesResponse result = mailgunStatisticsApi.getAggregateDevices(TEST_DOMAIN);

        assertNotNull(result);
        assertNotNull(result.getDevices());
        assertEquals(1, result.getDevices().size());
    }

    @Test
    void getAggregateCountriesTest() {
        Map<String, Object> countries = new HashMap<>();
        countries.put("US", Collections.singletonMap("opened", 200));
        StatsAggregateCountriesResponse expected = StatsAggregateCountriesResponse.builder()
                .countries(countries)
                .build();

        stubFor(get(urlPathEqualTo(API_BASE + "/" + TEST_DOMAIN + "/aggregates/countries"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(StringUtil.asJsonString(expected))));

        StatsAggregateCountriesResponse result = mailgunStatisticsApi.getAggregateCountries(TEST_DOMAIN);

        assertNotNull(result);
        assertNotNull(result.getCountries());
        assertEquals(1, result.getCountries().size());
    }
}
