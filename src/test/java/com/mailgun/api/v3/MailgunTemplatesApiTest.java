package com.mailgun.api.v3;

import com.mailgun.api.MailgunApi;
import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.templates.CopyTemplateItem;
import com.mailgun.model.templates.CopyTemplateRequest;
import com.mailgun.model.templates.CopyTemplateResponse;
import com.mailgun.model.templates.TemplateAllVersionsResponse;
import com.mailgun.model.templates.TemplateRequest;
import com.mailgun.model.templates.TemplateResponse;
import com.mailgun.model.templates.TemplateStatusResponse;
import com.mailgun.model.templates.TemplateVersionRequest;
import com.mailgun.model.templates.TemplateVersionResponse;
import com.mailgun.model.templates.TemplateWithMessageResponse;
import com.mailgun.model.templates.TemplateWithVersionResponse;
import com.mailgun.model.templates.TemplatesResult;
import com.mailgun.model.templates.UpdateTemplateVersionRequest;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MailgunTemplatesApiTest extends WireMockBaseTest {

    private static final String API_BASE = "/" + MailgunApi.getApiVersion().getValue();
    private static final String TEMPLATES_PATH = API_BASE + "/" + TEST_DOMAIN + "/templates";
    private static final String TEMPLATE_NAME = "welcome";
    private static final String VERSION_TAG = "initial";

    private MailgunTemplatesApi templatesApi;

    @BeforeEach
    void setUp() {
        templatesApi = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunTemplatesApi.class);
    }

    @Test
    void getAllTemplatesSuccessTest() {
        String body = "{\"items\":[{\"name\":\"welcome\",\"description\":\"Hi\"}],\"paging\":{}}";
        stubFor(get(urlPathEqualTo(TEMPLATES_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        TemplatesResult result = templatesApi.getAllTemplates(TEST_DOMAIN);

        assertNotNull(result.getItems());
        assertEquals(1, result.getItems().size());
        assertEquals(TEMPLATE_NAME, result.getItems().get(0).getName());
    }

    @Test
    void getTemplateSuccessTest() {
        String body = "{\"template\":{\"name\":\"welcome\",\"description\":\"Hi\"}}";
        stubFor(get(urlPathEqualTo(TEMPLATES_PATH + "/" + TEMPLATE_NAME))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        TemplateResponse result = templatesApi.getTemplate(TEST_DOMAIN, TEMPLATE_NAME);

        assertEquals(TEMPLATE_NAME, result.getTemplate().getName());
    }

    @Test
    void getActiveTemplateVersionContentSuccessTest() {
        String body = "{\"template\":{\"name\":\"welcome\",\"version\":{\"tag\":\"initial\"}}}";
        stubFor(get(urlEqualTo(TEMPLATES_PATH + "/" + TEMPLATE_NAME + "?active=yes"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        TemplateWithVersionResponse result = templatesApi.getActiveTemplateVersionContent(TEST_DOMAIN, TEMPLATE_NAME);

        assertEquals(TEMPLATE_NAME, result.getTemplate().getName());
        assertEquals(VERSION_TAG, result.getTemplate().getVersion().getTag());
    }

    @Test
    void getTemplateWithVersionSuccessTest() {
        String body = "{\"template\":{\"name\":\"welcome\",\"version\":{\"tag\":\"initial\"}}}";
        stubFor(get(urlEqualTo(TEMPLATES_PATH + "/" + TEMPLATE_NAME + "?version_name=" + VERSION_TAG))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        TemplateWithVersionResponse result = templatesApi.getTemplateWithVersion(TEST_DOMAIN, TEMPLATE_NAME, VERSION_TAG);

        assertEquals(TEMPLATE_NAME, result.getTemplate().getName());
        assertEquals(VERSION_TAG, result.getTemplate().getVersion().getTag());
    }

    @Test
    void storeNewTemplateSuccessTest() {
        String body = "{\"message\":\"template created\",\"template\":{\"name\":\"welcome\"}}";
        stubFor(post(urlPathEqualTo(TEMPLATES_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Content-Type", containing("multipart/form-data"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        TemplateRequest request = TemplateRequest.builder()
                .name(TEMPLATE_NAME)
                .description("Domain welcome")
                .build();

        TemplateWithMessageResponse result = templatesApi.storeNewTemplate(TEST_DOMAIN, request);

        assertEquals("template created", result.getMessage());
        assertEquals(TEMPLATE_NAME, result.getTemplate().getName());
    }

    @Test
    void updateTemplateSuccessTest() {
        String body = "{\"message\":\"updated\",\"template\":{\"name\":\"welcome\"}}";
        stubFor(put(urlPathEqualTo(TEMPLATES_PATH + "/" + TEMPLATE_NAME))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Content-Type", containing("multipart/form-data"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        TemplateStatusResponse result = templatesApi.updateTemplate(TEST_DOMAIN, TEMPLATE_NAME, "New description");

        assertEquals("updated", result.getMessage());
    }

    @Test
    void deleteTemplateSuccessTest() {
        String body = "{\"message\":\"deleted\",\"template\":{\"name\":\"welcome\"}}";
        stubFor(delete(urlPathEqualTo(TEMPLATES_PATH + "/" + TEMPLATE_NAME))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        TemplateStatusResponse result = templatesApi.deleteTemplate(TEST_DOMAIN, TEMPLATE_NAME);

        assertEquals("deleted", result.getMessage());
    }

    @Test
    void deleteAllTemplatesInDomainSuccessTest() {
        String body = "{\"message\":\"deleted\"}";
        stubFor(delete(urlPathEqualTo(TEMPLATES_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        ResponseWithMessage result = templatesApi.deleteAllTemplatesInDomain(TEST_DOMAIN);

        assertEquals("deleted", result.getMessage());
    }

    @Test
    void getAllTemplateVersionsSuccessTest() {
        String body = "{\"template\":{\"name\":\"welcome\",\"versions\":[{\"tag\":\"initial\"}]},\"paging\":{}}";
        stubFor(get(urlPathEqualTo(TEMPLATES_PATH + "/" + TEMPLATE_NAME + "/versions"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        TemplateAllVersionsResponse result = templatesApi.getAllTemplateVersions(TEST_DOMAIN, TEMPLATE_NAME);

        assertEquals(TEMPLATE_NAME, result.getTemplate().getName());
        assertEquals(1, result.getTemplate().getVersions().size());
    }

    @Test
    void createNewTemplateVersionSuccessTest() {
        String body = "{\"message\":\"version created\",\"template\":{\"name\":\"welcome\"}}";
        stubFor(post(urlPathEqualTo(TEMPLATES_PATH + "/" + TEMPLATE_NAME + "/versions"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Content-Type", containing("multipart/form-data"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        TemplateVersionRequest request = TemplateVersionRequest.builder()
                .tag("v2")
                .template("<html></html>")
                .build();

        TemplateWithMessageResponse result = templatesApi.createNewTemplateVersion(TEST_DOMAIN, TEMPLATE_NAME, request);

        assertEquals("version created", result.getMessage());
    }

    @Test
    void getSpecifiedVersionTemplateContentSuccessTest() {
        String body = "{\"template\":{\"name\":\"welcome\",\"version\":{\"tag\":\"initial\"}}}";
        stubFor(get(urlPathEqualTo(TEMPLATES_PATH + "/" + TEMPLATE_NAME + "/versions/" + VERSION_TAG))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        TemplateWithVersionResponse result = templatesApi.getSpecifiedVersionTemplateContent(
                TEST_DOMAIN, TEMPLATE_NAME, VERSION_TAG);

        assertEquals(VERSION_TAG, result.getTemplate().getVersion().getTag());
    }

    @Test
    void updateSpecificTemplateVersionSuccessTest() {
        String body = "{\"message\":\"updated\",\"template\":{\"name\":\"welcome\",\"version\":{\"tag\":\"initial\"}}}";
        stubFor(put(urlPathEqualTo(TEMPLATES_PATH + "/" + TEMPLATE_NAME + "/versions/" + VERSION_TAG))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Content-Type", containing("multipart/form-data"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        UpdateTemplateVersionRequest request = UpdateTemplateVersionRequest.builder()
                .comment("updated comment")
                .build();

        TemplateVersionResponse result = templatesApi.updateSpecificTemplateVersion(
                TEST_DOMAIN, TEMPLATE_NAME, VERSION_TAG, request);

        assertEquals("updated", result.getMessage());
    }

    @Test
    void deleteSpecificTemplateVersionSuccessTest() {
        String body = "{\"message\":\"deleted\",\"template\":{\"name\":\"welcome\",\"version\":{\"tag\":\"initial\"}}}";
        stubFor(delete(urlPathEqualTo(TEMPLATES_PATH + "/" + TEMPLATE_NAME + "/versions/" + VERSION_TAG))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        TemplateVersionResponse result = templatesApi.deleteSpecificTemplateVersion(
                TEST_DOMAIN, TEMPLATE_NAME, VERSION_TAG);

        assertEquals("deleted", result.getMessage());
    }

    @Test
    void copyTemplateVersionSuccessTest() {
        String newVersion = "initial-copy";
        String body = "{\"message\":\"copied\",\"template\":{\"name\":\"welcome\"}}";
        stubFor(put(urlPathEqualTo(TEMPLATES_PATH + "/" + TEMPLATE_NAME + "/versions/" + VERSION_TAG + "/copy/" + newVersion))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        TemplateWithMessageResponse result = templatesApi.copyTemplateVersion(
                TEST_DOMAIN, TEMPLATE_NAME, VERSION_TAG, newVersion);

        assertEquals("copied", result.getMessage());
    }

    @Test
    void renameTemplateSuccessTest() {
        String newName = "welcome-v2";
        String body = "{\"message\":\"renamed\",\"template\":{\"name\":\"welcome-v2\"}}";
        stubFor(put(urlPathEqualTo(TEMPLATES_PATH + "/" + TEMPLATE_NAME + "/rename/" + newName))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        TemplateWithMessageResponse result = templatesApi.renameTemplate(TEST_DOMAIN, TEMPLATE_NAME, newName);

        assertEquals("renamed", result.getMessage());
        assertEquals(newName, result.getTemplate().getName());
    }

    @Test
    void copyTemplateSuccessTest() {
        String body = "{\"message\":\"copied\",\"failed_copies\":[]}";
        stubFor(put(urlPathEqualTo(TEMPLATES_PATH + "/" + TEMPLATE_NAME + "/copy"))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .withHeader("Content-Type", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        CopyTemplateRequest request = CopyTemplateRequest.builder()
                .request(CopyTemplateItem.builder()
                        .accountId("acct-1")
                        .name("copy-welcome")
                        .build())
                .build();

        CopyTemplateResponse result = templatesApi.copyTemplate(TEST_DOMAIN, TEMPLATE_NAME, request);

        assertEquals("copied", result.getMessage());
        assertNotNull(result.getFailedCopies());
        assertTrue(result.getFailedCopies().isEmpty());
    }
}
