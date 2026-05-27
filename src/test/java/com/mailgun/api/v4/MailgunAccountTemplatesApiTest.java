package com.mailgun.api.v4;

import com.mailgun.api.WireMockBaseTest;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.templates.CopyTemplateItem;
import com.mailgun.model.templates.CopyTemplateRequest;
import com.mailgun.model.templates.CopyTemplateResponse;
import com.mailgun.model.templates.TemplateRequest;
import com.mailgun.model.templates.TemplateWithMessageResponse;
import com.mailgun.model.templates.TemplatesResult;
import com.mailgun.utils.TestHeadersUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.mailgun.constants.TestConstants.TEST_API_KEY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MailgunAccountTemplatesApiTest extends WireMockBaseTest {

    private static final String TEMPLATES_PATH = "/" + MailgunAccountTemplatesApi.getApiVersion().getValue() + "/templates";
    private static final String TEMPLATE_NAME = "welcome";

    private MailgunAccountTemplatesApi accountTemplatesApi;

    @BeforeEach
    void setUp() {
        accountTemplatesApi = MailgunClient.config(wireMockServer.baseUrl(), TEST_API_KEY)
                .createApi(MailgunAccountTemplatesApi.class);
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

        TemplatesResult result = accountTemplatesApi.getAllTemplates();

        assertNotNull(result.getItems());
        assertEquals(1, result.getItems().size());
        assertEquals(TEMPLATE_NAME, result.getItems().get(0).getName());
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
                .description("Account welcome")
                .build();

        TemplateWithMessageResponse result = accountTemplatesApi.storeNewTemplate(request);

        assertEquals("template created", result.getMessage());
        assertEquals(TEMPLATE_NAME, result.getTemplate().getName());
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

        TemplateWithMessageResponse result = accountTemplatesApi.renameTemplate(TEMPLATE_NAME, newName);

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

        CopyTemplateResponse result = accountTemplatesApi.copyTemplate(TEMPLATE_NAME, request);

        assertEquals("copied", result.getMessage());
        assertNotNull(result.getFailedCopies());
        assertTrue(result.getFailedCopies().isEmpty());
    }

    @Test
    void deleteAllTemplatesSuccessTest() {
        String body = "{\"message\":\"deleted\"}";
        stubFor(delete(urlPathEqualTo(TEMPLATES_PATH))
                .withHeader("Authorization", equalTo(TestHeadersUtils.getExpectedAuthHeader()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)));

        ResponseWithMessage result = accountTemplatesApi.deleteAllTemplates();

        assertEquals("deleted", result.getMessage());
    }
}
