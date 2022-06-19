package com.mailgun.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mailgun.api.MailgunApi;
import com.mailgun.util.ConsoleLogger;
import com.mailgun.util.MailgunApiUtil;
import com.mailgun.util.ObjectMapperUtil;
import feign.AsyncClient;
import feign.AsyncFeign;
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
import lombok.experimental.UtilityClass;

import static com.mailgun.util.Constants.DEFAULT_BASE_URL_US_REGION;

/**
 * <p>
 * Client for accessing Mailgun API
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api_reference.html">API Reference</a>
 */
@UtilityClass
public class MailgunClient {

    private static final ObjectMapper OBJECT_MAPPER = ObjectMapperUtil.getObjectMapper();
    private static final FormEncoder ENCODER = new FormEncoder(new JacksonEncoder(OBJECT_MAPPER));
    private static final JacksonDecoder DECODER = new JacksonDecoder(OBJECT_MAPPER);
    private static final FieldQueryMapEncoder QUERY_MAP_ENCODER = new FieldQueryMapEncoder();

    /**
     * <p>
     * Create {@link MailgunClientBuilder} with the default configuration.
     * </p>
     *
     * @param baseUrl base URL
     * @param apiKey primary account API key
     * @return {@link MailgunClientBuilder} with the default configuration
     */
    public MailgunClientBuilder config(String baseUrl, String apiKey) {
        return new MailgunClientBuilder(baseUrl, apiKey);
    }

    /**
     * <p>
     * Create {@link MailgunClientBuilder} with the default configuration.
     * </p>
     *
     * @param apiKey primary account API key
     * @return {@link MailgunClientBuilder} with the default configuration
     */
    public MailgunClientBuilder config(String apiKey) {
        return new MailgunClientBuilder(apiKey);
    }

    public static class MailgunClientBuilder {

        private Logger.Level logLevel = Logger.Level.BASIC;
        private Retryer retryer = new Retryer.Default();
        private Logger logger = new ConsoleLogger();
        private ErrorDecoder errorDecoder = new ErrorDecoder.Default();
        private Request.Options options = new Request.Options();
        private AsyncClient<Object> client = null;

        private String baseUrl = DEFAULT_BASE_URL_US_REGION;
        private final String apiKey;

        /**
         * <p>
         * Create {@link MailgunClientBuilder} with the default configuration.
         * </p>
         *
         * @param baseUrl base URL
         * @param apiKey primary account API key
         */
        private MailgunClientBuilder(String baseUrl, String apiKey) {
            this.baseUrl = baseUrl;
            this.apiKey = apiKey;
        }

        /**
         * <p>
         * Create {@link MailgunClientBuilder} with the default configuration.
         * </p>
         *
         * @param apiKey primary account API key
         */
        private MailgunClientBuilder(String apiKey) {
            this.apiKey = apiKey;
        }

        /**
         * <p>
         * You can override the default feign async client {@link AsyncClient.Default}
         * </p>
         *
         * @param client implementation of {@link AsyncClient}
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public MailgunClientBuilder client(AsyncClient<Object> client) {
            this.client = client;
            return this;
        }

        /**
         * <p>
         * You can override the default level of logging {@link MailgunClientBuilder#logLevel}.
         * </p>
         *
         * @param logLevel {@link Logger.Level}
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public MailgunClientBuilder logLevel(Logger.Level logLevel) {
            this.logLevel = logLevel;
            return this;
        }

        /**
         * <p>
         * You can override the default retryer {@link MailgunClientBuilder#retryer}.
         * </p>
         *
         * @param retryer implementation of {@link Retryer}
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public MailgunClientBuilder retryer(Retryer retryer) {
            this.retryer = retryer;
            return this;
        }

        /**
         * <p>
         * You can override the default logger {@link MailgunClientBuilder#logger}.
         * </p>
         *
         * @param logger implementation of {@link Logger}
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public MailgunClientBuilder logger(Logger logger) {
            this.logger = logger;
            return this;
        }

        /**
         * <p>
         * You can override the default error decoder {@link MailgunClientBuilder#errorDecoder}.
         * </p>
         *
         * @param errorDecoder implementation of {@link ErrorDecoder}
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public MailgunClientBuilder errorDecoder(ErrorDecoder errorDecoder) {
            this.errorDecoder = errorDecoder;
            return this;
        }

        /**
         * <p>
         * You can override the default the per-request settings {@link MailgunClientBuilder#options}.
         * </p>
         *
         * @param options {@link Request.Options}
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public MailgunClientBuilder options(Request.Options options) {
            this.options = options;
            return this;
        }

        @SuppressWarnings("unchecked")
        public <T> T createApi(Class<? extends MailgunApi> apiType) {
            String url = MailgunApiUtil.getFullUrl(apiType, baseUrl);
            return getFeignBuilder()
                    .target((Class<T>) apiType, url);
        }

        @SuppressWarnings("unchecked")
        public <T> T createAsyncApi(Class<? extends MailgunApi> apiType) {
            String url = MailgunApiUtil.getFullUrl(apiType, baseUrl);
            return getAsyncFeignBuilder()
                .target((Class<T>) apiType, url);
        }

        @SuppressWarnings("unchecked")
        public <T> T createApiWithAbsoluteUrl(Class<? extends MailgunApi> apiType) {
            return getFeignBuilder()
                .target((Class<T>) apiType, baseUrl);
        }

        private Feign.Builder getFeignBuilder() {
            return Feign.builder()
                    .logLevel(logLevel)
                    .retryer(retryer)
                    .logger(logger)
                    .encoder(ENCODER)
                    .decoder(DECODER)
                    .queryMapEncoder(QUERY_MAP_ENCODER)
                    .errorDecoder(errorDecoder)
                    .options(options)
                    .requestInterceptor(new BasicAuthRequestInterceptor("api", apiKey));
        }

        private AsyncFeign.AsyncBuilder<?> getAsyncFeignBuilder() {
            return AsyncFeign.asyncBuilder()
                .logLevel(logLevel)
                .logger(logger)
                .encoder(ENCODER)
                .decoder(DECODER)
                .queryMapEncoder(QUERY_MAP_ENCODER)
                .errorDecoder(errorDecoder)
                .options(options)
                .client(client)
                .requestInterceptor(new BasicAuthRequestInterceptor("api", apiKey));
        }
    }

}
