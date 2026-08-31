package com.mailgun.api.v1;

import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.enums.ApiKeyKind;
import com.mailgun.enums.ApiKeyRole;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.keys.ApiKeyCreateRequest;
import com.mailgun.model.keys.ApiKeyCreateResponse;
import com.mailgun.model.keys.ApiKeysListQuery;
import com.mailgun.model.keys.ApiKeysListResponse;
import com.mailgun.model.keys.PublicApiKeyResponse;
import com.mailgun.utils.TestHeadersUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.mailgun.constants.TestConstants.TEST_API_KEY;
import static com.mailgun.constants.TestConstants.TEST_DOMAIN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

class MailgunKeysApiTest extends WireMockBaseTest {

    private static final String KEYS_PATH = "/v1/keys";
    private static final String KEY_ID = "key-id-1";

    private MailgunKeysApi api;

    @BeforeEach
    void setUp() {
        api = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunKeysApi.class);
    }

    @Test
    void listApiKeysTest() {
        stubFor(get(urlEqualTo(KEYS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(jsonResponse("{\"total_count\":1,\"items\":[{" +
                        "\"id\":\"" + KEY_ID + "\",\"description\":\"sending key\"," +
                        "\"kind\":\"domain\",\"role\":\"sending\"," +
                        "\"created_at\":\"2026-01-23T10:16:00\"," +
                        "\"updated_at\":\"2026-01-23T10:17:00\"," +
                        "\"is_disabled\":false,\"domain_name\":\"" + TEST_DOMAIN + "\"," +
                        "\"requestor\":null,\"user_name\":null}]}")));

        ApiKeysListResponse result = api.listApiKeys();

        assertEquals(1, result.getTotalCount());
        assertEquals(1, result.getItems().size());
        assertEquals(KEY_ID, result.getItems().get(0).getId());
        assertEquals("domain", result.getItems().get(0).getKind());
        assertEquals("sending", result.getItems().get(0).getRole());
        assertEquals(TEST_DOMAIN, result.getItems().get(0).getDomainName());
        assertFalse(result.getItems().get(0).getIsDisabled());
        assertNull(result.getItems().get(0).getRequestor());
    }

    @Test
    void listApiKeysWithFiltersTest() {
        stubFor(get(urlPathEqualTo(KEYS_PATH))
                .withQueryParam("domain_name", equalTo(TEST_DOMAIN))
                .withQueryParam("kind", equalTo("domain"))
                .willReturn(jsonResponse("{\"total_count\":0,\"items\":[]}")));

        ApiKeysListResponse result = api.listApiKeys(ApiKeysListQuery.builder()
                .domain_name(TEST_DOMAIN)
                .kindEnum(ApiKeyKind.DOMAIN)
                .build());

        assertEquals(0, result.getTotalCount());
        assertEquals(0, result.getItems().size());
    }

    @Test
    void createApiKeyTest() {
        stubFor(post(urlPathEqualTo(KEYS_PATH))
                .withHeader("Content-Type", containing("multipart/form-data"))
                .withRequestBody(containing("name=\"domain_name\""))
                .withRequestBody(containing(TEST_DOMAIN))
                .withRequestBody(containing("name=\"role\""))
                .withRequestBody(containing("sending"))
                .willReturn(jsonResponse("{\"message\":\"key created\",\"key\":{" +
                        "\"id\":\"" + KEY_ID + "\",\"description\":\"sending key\"," +
                        "\"kind\":\"domain\",\"role\":\"sending\"," +
                        "\"created_at\":\"2026-01-23T10:16:00\"," +
                        "\"updated_at\":\"2026-01-23T10:16:00\"," +
                        "\"is_disabled\":false,\"domain_name\":\"" + TEST_DOMAIN + "\"," +
                        "\"requestor\":null,\"user_name\":null," +
                        "\"secret\":\"test-created-api-key\"}}")));

        ApiKeyCreateResponse result = api.createApiKey(ApiKeyCreateRequest.builder()
                .domainName(TEST_DOMAIN)
                .kindEnum(ApiKeyKind.DOMAIN)
                .description("sending key")
                .expiration(3600)
                .roleEnum(ApiKeyRole.SENDING)
                .build());

        assertEquals("key created", result.getMessage());
        assertEquals(KEY_ID, result.getKey().getId());
        assertEquals(ApiKeyKind.DOMAIN, result.getKey().getKindEnum().orElseThrow());
        assertEquals(ApiKeyRole.SENDING, result.getKey().getRoleEnum().orElseThrow());
        assertEquals("test-created-api-key", result.getKey().getSecret());
    }

    @Test
    void deleteApiKeyTest() {
        stubFor(delete(urlEqualTo(KEYS_PATH + "/" + KEY_ID))
                .willReturn(jsonResponse("{\"message\":\"key deleted\"}")));

        ResponseWithMessage result = api.deleteApiKey(KEY_ID);

        assertEquals("key deleted", result.getMessage());
    }

    @Test
    void regeneratePublicApiKeyTest() {
        stubFor(post(urlEqualTo(KEYS_PATH + "/public"))
                .willReturn(jsonResponse("{\"key\":\"test-public-api-key\"," +
                        "\"message\":\"public API key regenerated\"}")));

        PublicApiKeyResponse result = api.regeneratePublicApiKey();

        assertEquals("test-public-api-key", result.getKey());
        assertEquals("public API key regenerated", result.getMessage());
    }

    private static com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder jsonResponse(String body) {
        return aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(body);
    }
}
