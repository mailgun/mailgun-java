package com.mailgun.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mailgun.api.v3.MailgunMessagesApi;
import com.mailgun.util.ObjectMapperUtil;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.ErrorDecoder;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.querymap.FieldQueryMapEncoder;
import feign.slf4j.Slf4jLogger;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static com.mailgun.constants.TestConstants.TEST_API_KEY;
import static com.mailgun.util.Constants.DEFAULT_BASE_URL_US_REGION;
import static com.mailgun.util.Constants.EU_REGION_BASE_URL;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MailgunClientTest {

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
                .logger(new Slf4jLogger(MailgunMessagesApi.class))
                .encoder(new FormEncoder(new JacksonEncoder(objectMapper)))
                .decoder(new JacksonDecoder(objectMapper))
                .queryMapEncoder(new FieldQueryMapEncoder())
                .errorDecoder(new ErrorDecoder.Default())
                .options(new Request.Options(10, TimeUnit.SECONDS, 60, TimeUnit.SECONDS, true))
                .requestInterceptor(new BasicAuthRequestInterceptor("api", TEST_API_KEY))
                .target(MailgunMessagesApi.class, DEFAULT_BASE_URL_US_REGION);

        assertNotNull(mailgunMessagesApi);
    }

}
