package com.mailgun.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mailgun.api.MailgunApi;
import com.mailgun.api.v3.MailgunMessagesApi;
import com.mailgun.enums.MailgunRegion;
import com.mailgun.util.ConsoleLogger;
import com.mailgun.util.ObjectMapperUtil;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import feign.AsyncClient;
import feign.Client;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;
import feign.Retryer;
import feign.RequestLine;
import feign.Response;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.ErrorDecoder;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.querymap.FieldQueryMapEncoder;

import static com.mailgun.constants.TestConstants.TEST_API_KEY;
import static com.mailgun.util.Constants.DEFAULT_BASE_URL_US_REGION;
import static com.mailgun.util.Constants.EU_REGION_BASE_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MailgunClientTest {

    private interface TestApi extends MailgunApi {

        @RequestLine("GET /ping")
        Response ping();
    }

//    Default configuration.
    @Test
    void mailgunClientBuildDefaultTest() {
//        For US servers
        MailgunMessagesApi mailgunMessagesApiUS = MailgunClient.config(TEST_API_KEY)
                .createApi(MailgunMessagesApi.class);

        assertNotNull(mailgunMessagesApiUS);

//        For EU servers
        MailgunMessagesApi mailgunMessagesApiEU = MailgunClient.config(EU_REGION_BASE_URL, TEST_API_KEY)
                .createApi(MailgunMessagesApi.class);

        assertNotNull(mailgunMessagesApiEU);
    }

//    You can specify your own logLevel, retryer, logger, errorDecoder, options.
    @Test
    void mailgunClientBuildAllConfigurationTest() {
        MailgunMessagesApi mailgunMessagesApi = MailgunClient.config(TEST_API_KEY)
                .logLevel(Logger.Level.NONE)
                .retryer(new Retryer.Default())
                .logger(new Logger.NoOpLogger())
                .errorDecoder(new ErrorDecoder.Default())
                .options(new Request.Options(10, TimeUnit.SECONDS, 60, TimeUnit.SECONDS, true))
                .createApi(MailgunMessagesApi.class);

        assertNotNull(mailgunMessagesApi);
    }

//    Or create Mailgun Api by yourself using Feign client.
    @Test
    void customClientConfigurationTest() {
        ObjectMapper objectMapper = ObjectMapperUtil.getObjectMapper();

        MailgunMessagesApi mailgunMessagesApi = Feign.builder()
                .logLevel(Logger.Level.FULL)
                .retryer(new Retryer.Default())
                .logger(new ConsoleLogger())
                .encoder(new FormEncoder(new JacksonEncoder(objectMapper)))
                .decoder(new JacksonDecoder(objectMapper))
                .queryMapEncoder(new FieldQueryMapEncoder())
                .errorDecoder(new ErrorDecoder.Default())
                .options(new Request.Options(10, TimeUnit.SECONDS, 60, TimeUnit.SECONDS, true))
                .requestInterceptor(new BasicAuthRequestInterceptor("api", TEST_API_KEY))
                .target(MailgunMessagesApi.class, DEFAULT_BASE_URL_US_REGION);

        assertNotNull(mailgunMessagesApi);
    }

    @Test
    void createApiWithRequestInterceptorTest() {
        RequestInterceptor customInterceptor = requestTemplate ->
                requestTemplate.header("Custom-Header", "CustomValue");

        MailgunMessagesApi mailgunMessagesApi = MailgunClient.config(TEST_API_KEY)
                .createApiWithRequestInterceptor(MailgunMessagesApi.class, customInterceptor);

        assertNotNull(mailgunMessagesApi);
    }

    @Test
    void createAsyncApiTest() {
        MailgunMessagesApi mailgunMessagesApi = MailgunClient.config(TEST_API_KEY)
                .createAsyncApi(MailgunMessagesApi.class);

        assertNotNull(mailgunMessagesApi);
    }

    @Test
    void createApiWithAbsoluteUrlTest() {
        MailgunMessagesApi mailgunMessagesApi = MailgunClient.config(DEFAULT_BASE_URL_US_REGION, TEST_API_KEY)
                .createApiWithAbsoluteUrl(MailgunMessagesApi.class);

        assertNotNull(mailgunMessagesApi);
    }

    @Test
    void clientMethodTest() {
        AsyncClient<Object> customAsyncClient = new AsyncClient.Default<>(
                new Client.Default(null, null),
                Executors.newSingleThreadExecutor()
        );

        MailgunClient.MailgunClientBuilder builder = MailgunClient.config(TEST_API_KEY)
                .client(customAsyncClient);

        assertNotNull(builder);
    }

    @Test
    void topLevelBuilderConfigurationTest() {
        RecordingClient client = new RecordingClient();

        TestApi api = MailgunClient.builder(TEST_API_KEY)
            .region(MailgunRegion.EU)
            .timeouts(2, 7, TimeUnit.SECONDS)
            .retryPolicy(10, 100, 3)
            .customHeader("X-Client", "mailgun-java")
            .logging(Logger.Level.FULL)
            .syncClient(client)
            .createApi(TestApi.class);

        api.ping();

        assertTrue(client.request.url().startsWith(EU_REGION_BASE_URL + "/v3/"));
        assertEquals(Collections.singletonList("mailgun-java"), client.request.headers().get("X-Client"));
        assertTrue(client.request.headers().containsKey("Authorization"));
        assertEquals(TimeUnit.SECONDS.toMillis(2), client.options.connectTimeoutMillis());
        assertEquals(TimeUnit.SECONDS.toMillis(7), client.options.readTimeoutMillis());
    }

    @Test
    void topLevelBuilderRejectsInvalidSensitiveConfigurationTest() {
        assertThrows(IllegalArgumentException.class,
            () -> MailgunClient.builder(TEST_API_KEY).customHeader("Authorization", "secret"));
        assertThrows(IllegalArgumentException.class,
            () -> MailgunClient.builder(TEST_API_KEY).timeouts(-1, 1, TimeUnit.SECONDS));
        assertThrows(IllegalArgumentException.class,
            () -> MailgunClient.builder(TEST_API_KEY).retryPolicy(1, 1, 0));
        assertThrows(IllegalArgumentException.class,
            () -> MailgunClient.builder(TEST_API_KEY).proxy("localhost", 0));
    }

    @Test
    void proxyConfigurationTest() {
        MailgunClient.MailgunClientBuilder builder = MailgunClient.builder(TEST_API_KEY)
            .proxy("localhost", 8080);

        assertNotNull(builder);
    }

    private static class RecordingClient implements Client {

        private Request request;
        private Request.Options options;

        @Override
        public Response execute(Request request, Request.Options options) throws IOException {
            this.request = request;
            this.options = options;
            return Response.builder()
                .status(200)
                .reason("OK")
                .headers(Map.of())
                .request(request)
                .body(new byte[0])
                .build();
        }
    }
}
