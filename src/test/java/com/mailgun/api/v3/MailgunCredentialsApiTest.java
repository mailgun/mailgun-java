package com.mailgun.api.v3;

import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.credentials.SmtpCredentialCreateRequest;
import com.mailgun.model.credentials.SmtpCredentialDeleteResponse;
import com.mailgun.model.credentials.SmtpCredentialOperationResponse;
import com.mailgun.model.credentials.SmtpCredentialUpdateRequest;
import com.mailgun.model.credentials.SmtpCredentialsDeleteResponse;
import com.mailgun.model.credentials.SmtpCredentialsListQuery;
import com.mailgun.model.credentials.SmtpCredentialsListResponse;
import com.mailgun.model.domains.DomainCredentials;
import com.mailgun.utils.TestHeadersUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.mailgun.constants.TestConstants.TEST_API_KEY;
import static com.mailgun.constants.TestConstants.TEST_DOMAIN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MailgunCredentialsApiTest extends WireMockBaseTest {

    private static final String CREDENTIALS_PATH = "/v3/domains/" + TEST_DOMAIN + "/credentials";
    private static final String LOGIN = "sender@example.com";
    private static final String ENCODED_LOGIN = "sender%40example.com";
    private static final String PASSWORD = "test-smtp-password";

    private MailgunDomainsApi api;

    @BeforeEach
    void setUp() {
        api = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunDomainsApi.class);
    }

    @Test
    void getCredentialsTest() {
        stubFor(get(urlEqualTo(CREDENTIALS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(jsonResponse("{\"items\":[{\"mailbox\":\"" + LOGIN + "\"," +
                        "\"login\":\"" + LOGIN + "\",\"created_at\":\"Fri, 23 Jan 2026 10:16:00 +0000\"," +
                        "\"size_bytes\":null}],\"total_count\":1}")));

        SmtpCredentialsListResponse result = api.getCredentials(TEST_DOMAIN);

        assertEquals(1, result.getTotalCount());
        assertEquals(LOGIN, result.getItems().get(0).getMailbox());
        assertEquals(LOGIN, result.getItems().get(0).getLogin());
        assertEquals(2026, result.getItems().get(0).getCreatedAt().getYear());
        assertNull(result.getItems().get(0).getSizeBytes());
    }

    @Test
    void getCredentialsWithPaginationTest() {
        stubFor(get(urlPathEqualTo(CREDENTIALS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withQueryParam("skip", equalTo("10"))
                .withQueryParam("limit", equalTo("25"))
                .willReturn(jsonResponse("{\"items\":[],\"total_count\":0}")));

        SmtpCredentialsListResponse result = api.getCredentials(TEST_DOMAIN,
                SmtpCredentialsListQuery.builder().skip(10).limit(25).build());

        assertEquals(0, result.getTotalCount());
        assertEquals(0, result.getItems().size());
    }

    @Test
    void createCredentialsTest() {
        stubFor(post(urlPathEqualTo(CREDENTIALS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Content-Type", containing("multipart/form-data"))
                .withRequestBody(containing("name=\"login\""))
                .withRequestBody(containing(LOGIN))
                .withRequestBody(containing("name=\"system\""))
                .willReturn(jsonResponse("{\"message\":\"Created 1 credentials pair(s)\"," +
                        "\"note\":\"Store this password securely\"," +
                        "\"credentials\":{\"" + LOGIN + "\":\"" + PASSWORD + "\"}}")));

        SmtpCredentialOperationResponse result = api.createCredentials(TEST_DOMAIN,
                SmtpCredentialCreateRequest.builder().login(LOGIN).system(false).build());

        assertEquals("Created 1 credentials pair(s)", result.getMessage());
        assertEquals("Store this password securely", result.getNote());
        assertEquals(PASSWORD, result.getCredentials().get(LOGIN));
    }

    @Test
    void deleteAllCredentialsTest() {
        stubFor(delete(urlEqualTo(CREDENTIALS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(jsonResponse("{\"message\":\"All domain credentials have been deleted\",\"count\":2}")));

        SmtpCredentialsDeleteResponse result = api.deleteAllCredentials(TEST_DOMAIN);

        assertEquals("All domain credentials have been deleted", result.getMessage());
        assertEquals(2, result.getCount());
    }

    @Test
    void updateCredentialsTest() {
        stubFor(put(urlPathEqualTo(CREDENTIALS_PATH + "/" + ENCODED_LOGIN))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Content-Type", containing("multipart/form-data"))
                .withRequestBody(containing("name=\"password\""))
                .withRequestBody(containing(PASSWORD))
                .willReturn(jsonResponse("{\"message\":\"Password changed\"}")));

        SmtpCredentialOperationResponse result = api.updateCredentials(TEST_DOMAIN, LOGIN,
                SmtpCredentialUpdateRequest.builder().password(PASSWORD).build());

        assertEquals("Password changed", result.getMessage());
    }

    @Test
    void deleteCredentialTest() {
        stubFor(delete(urlEqualTo(CREDENTIALS_PATH + "/" + ENCODED_LOGIN))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(jsonResponse("{\"message\":\"Credentials have been deleted\"," +
                        "\"spec\":\"" + LOGIN + "\"}")));

        SmtpCredentialDeleteResponse result = api.deleteCredential(TEST_DOMAIN, LOGIN);

        assertEquals("Credentials have been deleted", result.getMessage());
        assertEquals(LOGIN, result.getSpec());
    }

    @Test
    void legacyCredentialMethodsRemainCompatibleTest() {
        stubFor(post(urlPathEqualTo(CREDENTIALS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withRequestBody(containing(LOGIN))
                .willReturn(jsonResponse("{\"message\":\"Created 1 credentials pair(s)\"}")));
        stubFor(put(urlPathEqualTo(CREDENTIALS_PATH + "/" + ENCODED_LOGIN))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withRequestBody(containing(PASSWORD))
                .willReturn(jsonResponse("{\"message\":\"Password changed\"}")));
        stubFor(delete(urlEqualTo(CREDENTIALS_PATH + "/" + ENCODED_LOGIN))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(jsonResponse("{\"message\":\"Credentials have been deleted\"}")));

        ResponseWithMessage created = api.createNewCredentials(TEST_DOMAIN,
                DomainCredentials.builder().login(LOGIN).password(PASSWORD).build());
        ResponseWithMessage updated = api.updateCredentials(TEST_DOMAIN, LOGIN, PASSWORD);
        ResponseWithMessage deleted = api.deleteCredentials(TEST_DOMAIN, LOGIN);

        assertEquals("Created 1 credentials pair(s)", created.getMessage());
        assertEquals("Password changed", updated.getMessage());
        assertEquals("Credentials have been deleted", deleted.getMessage());
    }

    private static com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder jsonResponse(String body) {
        return aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(body);
    }
}
