package com.mailgun.api.v5;

import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.enums.ApiVersion;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.accountmanagement.AccountOperationSuccessResponse;
import com.mailgun.model.accountmanagement.HttpSigningKeyResponse;
import com.mailgun.model.accountmanagement.SandboxRecipientResponse;
import com.mailgun.model.accountmanagement.SandboxRecipientsResponse;
import com.mailgun.model.accountmanagement.UpdateAccountFeaturesRequest;
import com.mailgun.model.accountmanagement.UpdateAccountSettingsQuery;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MailgunAccountManagementApiTest extends WireMockBaseTest {

    private static final String API_BASE = "/" + ApiVersion.V_5.getValue();
    private static final String ACCOUNTS_PATH = API_BASE + "/accounts";
    private static final String SANDBOX_RECIPIENTS_PATH = API_BASE + "/sandbox/auth_recipients";
    private static final String EMAIL = "recipient@example.com";
    private static final String SIGNING_KEY = "test-http-signing-key";

    private MailgunAccountManagementApi api;

    @BeforeEach
    void setUp() {
        api = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunAccountManagementApi.class);
    }

    @Test
    void updateAccountSettingsTest() {
        stubFor(put(urlPathEqualTo(ACCOUNTS_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withQueryParam("name", equalTo("New organization"))
                .withQueryParam("inactive_session_timeout", equalTo("30"))
                .withQueryParam("absolute_session_timeout", equalTo("120"))
                .withQueryParam("logout_redirect_url", equalTo("https://example.com/logout"))
                .willReturn(jsonResponse("{\"message\":\"Account info has been updated\"}")));

        ResponseWithMessage result = api.updateAccountSettings(UpdateAccountSettingsQuery.builder()
                .name("New organization")
                .inactive_session_timeout(30)
                .absolute_session_timeout(120)
                .logout_redirect_url("https://example.com/logout")
                .build());

        assertEquals("Account info has been updated", result.getMessage());
    }

    @Test
    void getHttpSigningKeyTest() {
        stubFor(get(urlEqualTo(ACCOUNTS_PATH + "/http_signing_key"))
                .willReturn(jsonResponse("{\"http_signing_key\":\"" + SIGNING_KEY + "\"}")));

        HttpSigningKeyResponse result = api.getHttpSigningKey();

        assertEquals(SIGNING_KEY, result.getHttpSigningKey());
    }

    @Test
    void regenerateHttpSigningKeyTest() {
        stubFor(post(urlEqualTo(ACCOUNTS_PATH + "/http_signing_key"))
                .willReturn(jsonResponse("{\"message\":\"regenerated HTTP signing key for account\","
                        + "\"http_signing_key\":\"" + SIGNING_KEY + "\"}")));

        HttpSigningKeyResponse result = api.regenerateHttpSigningKey();

        assertEquals("regenerated HTTP signing key for account", result.getMessage());
        assertEquals(SIGNING_KEY, result.getHttpSigningKey());
    }

    @Test
    void getSandboxAuthorizedRecipientsTest() {
        stubFor(get(urlEqualTo(SANDBOX_RECIPIENTS_PATH))
                .willReturn(jsonResponse("{\"recipients\":[{\"email\":\"" + EMAIL
                        + "\",\"activated\":true}]}")));

        SandboxRecipientsResponse result = api.getSandboxAuthorizedRecipients();

        assertEquals(1, result.getRecipients().size());
        assertEquals(EMAIL, result.getRecipients().get(0).getEmail());
        assertTrue(result.getRecipients().get(0).getActivated());
    }

    @Test
    void addSandboxAuthorizedRecipientTest() {
        stubFor(post(urlPathEqualTo(SANDBOX_RECIPIENTS_PATH))
                .withQueryParam("email", equalTo(EMAIL))
                .willReturn(jsonResponse("{\"recipient\":{\"email\":\"" + EMAIL
                        + "\",\"activated\":false}}")));

        SandboxRecipientResponse result = api.addSandboxAuthorizedRecipient(EMAIL);

        assertEquals(EMAIL, result.getRecipient().getEmail());
        assertFalse(result.getRecipient().getActivated());
    }

    @Test
    void deleteSandboxAuthorizedRecipientTest() {
        stubFor(delete(urlEqualTo(SANDBOX_RECIPIENTS_PATH + "/recipient%40example.com"))
                .willReturn(jsonResponse("{\"message\":\"Recipient removed\"}")));

        ResponseWithMessage result = api.deleteSandboxAuthorizedRecipient(EMAIL);

        assertEquals("Recipient removed", result.getMessage());
    }

    @Test
    void resendActivationEmailTest() {
        stubFor(post(urlEqualTo(ACCOUNTS_PATH + "/resend_activation_email"))
                .willReturn(jsonResponse("{\"success\":true}")));

        AccountOperationSuccessResponse result = api.resendActivationEmail();

        assertTrue(result.getSuccess());
    }

    @Test
    void updateAccountFeaturesTest() {
        stubFor(put(urlEqualTo(ACCOUNTS_PATH + "/features"))
                .withHeader("Content-Type", containing("application/x-www-form-urlencoded"))
                .withRequestBody(containing("webhooks_redact_pii="))
                .withRequestBody(containing("ai_insights="))
                .willReturn(jsonResponse("{\"success\":true}")));
        UpdateAccountFeaturesRequest request = UpdateAccountFeaturesRequest.builder()
                .webhooksRedactPii("{\"enabled\":false}")
                .aiInsights("{\"enabled\":false}")
                .build();

        AccountOperationSuccessResponse result = api.updateAccountFeatures(request);

        assertTrue(result.getSuccess());
    }

    private static com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder jsonResponse(String body) {
        return aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(body);
    }

}
