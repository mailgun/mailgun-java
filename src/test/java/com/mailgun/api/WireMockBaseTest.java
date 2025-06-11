package com.mailgun.api;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

@Slf4j
public abstract class WireMockBaseTest {

    protected static WireMockServer wireMockServer;

    @BeforeAll
    static void beforeAll() {
        wireMockServer = new WireMockServer();
        wireMockServer.addMockServiceRequestListener(
                WireMockBaseTest::requestReceived);
        wireMockServer.start();
    }

    @AfterAll
    static void afterAll() {
        wireMockServer.stop();
    }

    protected static void requestReceived(Request request, Response response) {
        log.info("WireMock request at URL: {}\n", request.getAbsoluteUrl());
        log.info("WireMock request headers: \n{}", request.getHeaders());
        log.info("WireMock request body: {}", request.getBodyAsString());
        log.info("WireMock response headers: \n{}", response.getHeaders());
        log.info("WireMock response body: {}", response.getBodyAsString());
    }

}
