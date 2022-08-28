package com.mailgun.api;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.mailgun.api.v3.MailgunStatisticsApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.enums.Duration;
import com.mailgun.enums.ResolutionPeriod;
import com.mailgun.enums.StatsEventType;
import com.mailgun.model.StatisticsOptions;
import com.mailgun.model.stats.Stats;
import com.mailgun.model.stats.StatsResult;
import com.mailgun.model.stats.StatsTotalValueObject;
import com.mailgun.util.StringUtil;
import com.mailgun.utils.TestHeadersUtils;

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

        stubFor(get(urlPathEqualTo("/" + MailgunApi.getApiVersion().getValue() + "/" + TEST_DOMAIN + "/stats/total"))
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

}
