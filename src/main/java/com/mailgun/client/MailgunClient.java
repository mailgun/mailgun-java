package com.mailgun.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mailgun.api.MailgunApi;
import com.mailgun.enums.MailgunRegion;
import com.mailgun.form.FormEncoder;
import com.mailgun.util.ConsoleLogger;
import com.mailgun.util.MailgunApiUtil;
import com.mailgun.util.ObjectMapperUtil;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import feign.AsyncClient;
import feign.AsyncFeign;
import feign.Client;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;
import feign.Retryer;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.ErrorDecoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
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
     * Create a top-level {@link MailgunClientBuilder} with the default US region configuration.
     *
     * @param apiKey primary account API key
     * @return {@link MailgunClientBuilder} with the default configuration
     */
    public MailgunClientBuilder builder(String apiKey) {
        return new MailgunClientBuilder(apiKey);
    }

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
        private Client syncClient = new OkHttpClient();
        private AsyncClient<Object> asyncClient;
        private final Map<String, String> customHeaders = new LinkedHashMap<>();

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
            this.asyncClient = Objects.requireNonNull(client, "client");
            return this;
        }

        /**
         * Override the default synchronous Feign client.
         *
         * @param client implementation of {@link Client}
         * @return this builder
         */
        public MailgunClientBuilder syncClient(Client client) {
            this.syncClient = Objects.requireNonNull(client, "client");
            return this;
        }

        /**
         * Select the Mailgun API region.
         *
         * @param region Mailgun API region
         * @return this builder
         */
        public MailgunClientBuilder region(MailgunRegion region) {
            this.baseUrl = Objects.requireNonNull(region, "region").getBaseUrl();
            return this;
        }

        /**
         * Configure connect and read timeouts while preserving the redirect setting.
         *
         * @param connectTimeout connect timeout
         * @param readTimeout read timeout
         * @param unit timeout unit
         * @return this builder
         */
        public MailgunClientBuilder timeouts(long connectTimeout, long readTimeout, TimeUnit unit) {
            Objects.requireNonNull(unit, "unit");
            if (connectTimeout < 0 || readTimeout < 0) {
                throw new IllegalArgumentException("Timeouts cannot be negative");
            }
            this.options = new Request.Options(connectTimeout, unit, readTimeout, unit, options.isFollowRedirects());
            return this;
        }

        /**
         * Route synchronous and default asynchronous requests through an HTTP proxy.
         *
         * @param host proxy host
         * @param port proxy port
         * @return this builder
         */
        public MailgunClientBuilder proxy(String host, int port) {
            if (host == null || host.trim().isEmpty()) {
                throw new IllegalArgumentException("Proxy host cannot be blank");
            }
            if (port < 1 || port > 65535) {
                throw new IllegalArgumentException("Proxy port must be between 1 and 65535");
            }
            return proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port)));
        }

        /**
         * Route synchronous and default asynchronous requests through a proxy.
         *
         * @param proxy proxy configuration
         * @return this builder
         */
        public MailgunClientBuilder proxy(Proxy proxy) {
            okhttp3.OkHttpClient okHttpClient = new okhttp3.OkHttpClient.Builder()
                .proxy(Objects.requireNonNull(proxy, "proxy"))
                .build();
            this.syncClient = new OkHttpClient(okHttpClient);
            return this;
        }

        /**
         * Configure the default exponential-backoff retry policy.
         *
         * @param initialIntervalMillis initial interval between attempts
         * @param maximumIntervalMillis maximum interval between attempts
         * @param maximumAttempts maximum number of attempts, including the first request
         * @return this builder
         */
        public MailgunClientBuilder retryPolicy(long initialIntervalMillis, long maximumIntervalMillis,
                                                 int maximumAttempts) {
            if (initialIntervalMillis < 0 || maximumIntervalMillis < 0 || maximumAttempts < 1) {
                throw new IllegalArgumentException("Retry intervals cannot be negative and attempts must be positive");
            }
            this.retryer = new Retryer.Default(initialIntervalMillis, maximumIntervalMillis, maximumAttempts);
            return this;
        }

        /**
         * Add a header to every request. Authorization headers must be configured through the API key or proxy.
         *
         * @param name header name
         * @param value header value
         * @return this builder
         */
        public MailgunClientBuilder customHeader(String name, String value) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Header name cannot be blank");
            }
            if ("authorization".equalsIgnoreCase(name) || "proxy-authorization".equalsIgnoreCase(name)) {
                throw new IllegalArgumentException("Authentication headers cannot be configured as custom headers");
            }
            customHeaders.put(name, Objects.requireNonNull(value, "value"));
            return this;
        }

        /**
         * Add headers to every request.
         *
         * @param headers header names and values
         * @return this builder
         */
        public MailgunClientBuilder customHeaders(Map<String, String> headers) {
            Objects.requireNonNull(headers, "headers").forEach(this::customHeader);
            return this;
        }

        /**
         * Enable logging through the default secret-redacting logger.
         *
         * @param level Feign logging level
         * @return this builder
         */
        public MailgunClientBuilder logging(Logger.Level level) {
            this.logger = new ConsoleLogger();
            this.logLevel = Objects.requireNonNull(level, "level");
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
         * <p>
         * The default {@link ConsoleLogger} redacts authentication headers and does not log request or response
         * bodies. A custom logger is responsible for applying equivalent protection to credentials and message data.
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

        public <T extends MailgunApi> T createApi(Class<T> apiType) {
            String url = MailgunApiUtil.getFullUrl(apiType, baseUrl);
            return getFeignBuilder().target(apiType, url);
        }

		public <T extends MailgunApi> T createApiWithRequestInterceptor(Class<T> apiType, RequestInterceptor requestInterceptor) {
			String url = MailgunApiUtil.getFullUrl(apiType, baseUrl);
			return getFeignBuilder().requestInterceptor(requestInterceptor).target(apiType, url);
		}

        public <T extends MailgunApi> T createAsyncApi(Class<T> apiType) {
            String url = MailgunApiUtil.getFullUrl(apiType, baseUrl);
            return getAsyncFeignBuilder().target(apiType, url);
        }

        public <T extends MailgunApi> T createApiWithAbsoluteUrl(Class<T> apiType) {
            return getFeignBuilder().target(apiType, baseUrl);
        }

        private Feign.Builder getFeignBuilder() {
            Feign.Builder builder = Feign.builder()
                    .client(syncClient)
                    .logLevel(logLevel)
                    .retryer(retryer)
                    .logger(logger)
                    .encoder(ENCODER)
                    .decoder(DECODER)
                    .queryMapEncoder(QUERY_MAP_ENCODER)
                    .errorDecoder(errorDecoder)
                    .options(options)
                    .requestInterceptor(new BasicAuthRequestInterceptor("api", apiKey));
            addCustomHeaders(builder);
            return builder;
        }

        private AsyncFeign.AsyncBuilder<?> getAsyncFeignBuilder() {
            AsyncFeign.AsyncBuilder<?> builder = AsyncFeign.builder()
                .logLevel(logLevel)
                .logger(logger)
                .encoder(ENCODER)
                .decoder(DECODER)
                .queryMapEncoder(QUERY_MAP_ENCODER)
                .errorDecoder(errorDecoder)
                .options(options)
                .client(getAsyncClient())
                .requestInterceptor(new BasicAuthRequestInterceptor("api", apiKey));
            addCustomHeaders(builder);
            return builder;
        }

        private AsyncClient<Object> getAsyncClient() {
            return asyncClient == null
                ? new AsyncClient.Default<>(syncClient, Executors.newSingleThreadExecutor())
                : asyncClient;
        }

        private void addCustomHeaders(Feign.Builder builder) {
            if (!customHeaders.isEmpty()) {
                builder.requestInterceptor(template -> customHeaders.forEach(template::header));
            }
        }

        private void addCustomHeaders(AsyncFeign.AsyncBuilder<?> builder) {
            if (!customHeaders.isEmpty()) {
                builder.requestInterceptor(template -> customHeaders.forEach(template::header));
            }
        }
    }

}
