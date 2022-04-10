package com.mailgun.integration;

import com.mailgun.api.v3.MailgunTemplatesApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.enums.Page;
import com.mailgun.model.PagingWithPivot;
import com.mailgun.model.templates.Template;
import com.mailgun.model.templates.TemplateAllVersionsResponse;
import com.mailgun.model.templates.TemplateNameResponse;
import com.mailgun.model.templates.TemplateRequest;
import com.mailgun.model.templates.TemplateResponse;
import com.mailgun.model.templates.TemplateStatusResponse;
import com.mailgun.model.templates.TemplateVersion;
import com.mailgun.model.templates.TemplateVersionRequest;
import com.mailgun.model.templates.TemplateVersionResponse;
import com.mailgun.model.templates.TemplateVersionTag;
import com.mailgun.model.templates.TemplateVersions;
import com.mailgun.model.templates.TemplateWithMessageResponse;
import com.mailgun.model.templates.TemplateWithVersion;
import com.mailgun.model.templates.TemplateWithVersionResponse;
import com.mailgun.model.templates.TemplateWithVersions;
import com.mailgun.model.templates.TemplatesResult;
import com.mailgun.model.templates.UpdateTemplateVersionRequest;
import com.mailgun.model.templates.VersionTag;
import feign.FeignException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.mailgun.constants.IntegrationTestConstants.TEMPLATE_1;
import static com.mailgun.constants.IntegrationTestConstants.TEMPLATE_2;
import static com.mailgun.constants.IntegrationTestConstants.TEMPLATE_COMMENT;
import static com.mailgun.constants.IntegrationTestConstants.TEMPLATE_DESCRIPTION;
import static com.mailgun.constants.IntegrationTestConstants.TEMPLATE_ENGINE;
import static com.mailgun.constants.IntegrationTestConstants.TEMPLATE_NAME;
import static com.mailgun.constants.IntegrationTestConstants.TEMPLATE_VERSION_TAG_1;
import static com.mailgun.constants.IntegrationTestConstants.TEMPLATE_VERSION_TAG_2;
import static com.mailgun.constants.IntegrationTestConstants.MAIN_DOMAIN;
import static com.mailgun.constants.IntegrationTestConstants.PRIVATE_API_KEY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled("Use these tests as examples for a real implementation.")
class MailgunTemplatesIntegrationTest {

    private static final String TEMPLATE_COMMENT_UPDATED = "Some comment updated";

    private static MailgunTemplatesApi mailgunTemplatesApi;

    @BeforeAll
    static void beforeAll() {
        mailgunTemplatesApi = MailgunClient.config(PRIVATE_API_KEY)
                .createApi(MailgunTemplatesApi.class);
    }

    @Test
    void storeNewTemplateSuccessTest() {
        TemplateRequest request = TemplateRequest.builder()
                .name(TEMPLATE_NAME)
                .description(TEMPLATE_DESCRIPTION)
                .template(TEMPLATE_1)
                .tag(TEMPLATE_VERSION_TAG_1)
                .engine(TEMPLATE_ENGINE)
                .comment(TEMPLATE_COMMENT)
                .build();

        TemplateWithMessageResponse result = mailgunTemplatesApi.storeNewTemplate(MAIN_DOMAIN, request);

        assertEquals("template has been stored", result.getMessage());
        TemplateWithVersion template = result.getTemplate();
        assertNotNull(template);
        assertNotNull(template.getId());
        assertEquals(TEMPLATE_NAME, template.getName());
        assertEquals(TEMPLATE_DESCRIPTION, template.getDescription());
        assertNotNull(template.getCreatedAt());
        TemplateVersion version = template.getVersion();
        assertNotNull(version.getId());
        assertEquals(true, version.getActive());
        assertEquals(TEMPLATE_VERSION_TAG_1, version.getTag());
        assertEquals(TEMPLATE_1, version.getTemplate());
        assertEquals(TEMPLATE_ENGINE, version.getEngine());
        assertNotNull(version.getMjml());
        assertNotNull(version.getCreatedAt());
        assertEquals(TEMPLATE_COMMENT, version.getComment());
    }

    @Test
    void getAllTemplatesSuccessTest() {
        TemplatesResult result = mailgunTemplatesApi.getAllTemplates(MAIN_DOMAIN);

        List<Template> items = result.getItems();
        assertTrue(CollectionUtils.isNotEmpty(items));
        assertTrue(items.size() > 2);
        assertNotNull(result.getPaging());
        Template template = items.get(0);
        assertNotNull(template.getId());
        assertNotNull(template.getName());
        assertNotNull(template.getDescription());
        assertNotNull(template.getCreatedAt());
    }

    @Test
    void getAllTemplatesQueryOptionsSuccessTest() {
        PagingWithPivot pagingWithPivot = PagingWithPivot.builder()
                .limit(2)
                .page(Page.NEXT)
                .pivot(TEMPLATE_NAME)
                .build();

        TemplatesResult result = mailgunTemplatesApi.getAllTemplates(MAIN_DOMAIN, pagingWithPivot);

        assertEquals(2, result.getItems().size());
    }

    @Test
    void getTemplateSuccessTest() {
        TemplateResponse result = mailgunTemplatesApi.getTemplate(MAIN_DOMAIN, TEMPLATE_NAME);

        Template template = result.getTemplate();
        assertNotNull(template);
        assertNotNull(template.getId());
        assertNotNull(template.getName());
        assertNotNull(template.getDescription());
        assertNotNull(template.getCreatedAt());
    }

    @Test
    void getTemplateWithActiveVersionContentSuccessTest() {
        TemplateWithVersionResponse result = mailgunTemplatesApi.getActiveTemplateVersionContent(MAIN_DOMAIN, TEMPLATE_NAME);

        TemplateWithVersion template = result.getTemplate();
        assertNotNull(template);
        assertNotNull(template.getId());
        assertNotNull(template.getName());
        assertNotNull(template.getDescription());
        assertNotNull(template.getCreatedAt());
        TemplateVersion version = template.getVersion();
        assertNotNull(version.getId());
        assertNotNull(version.getActive());
        assertNotNull(version.getTag());
        assertNotNull(version.getTemplate());
        assertNotNull(version.getEngine());
        assertNotNull(version.getMjml());
        assertNotNull(version.getCreatedAt());
        assertNotNull(version.getComment());
    }

    @Test
    void getSpecifiedVersionTemplateContentSuccessTest() {
        TemplateWithVersionResponse result = mailgunTemplatesApi.getSpecifiedVersionTemplateContent(MAIN_DOMAIN, TEMPLATE_NAME, TEMPLATE_VERSION_TAG_1);

        TemplateWithVersion template = result.getTemplate();
        assertNotNull(template);
        assertNotNull(template.getId());
        assertNotNull(template.getName());
        assertNotNull(template.getDescription());
        assertNotNull(template.getCreatedAt());
        TemplateVersion version = template.getVersion();
        assertNotNull(version.getId());
        assertNotNull(version.getActive());
        assertNotNull(version.getTag());
        assertNotNull(version.getTemplate());
        assertNotNull(version.getEngine());
        assertNotNull(version.getMjml());
        assertNotNull(version.getCreatedAt());
        assertNotNull(version.getComment());
    }

    @Test
    void updateTemplateSuccessTest() {
        TemplateStatusResponse result = mailgunTemplatesApi.updateTemplate(MAIN_DOMAIN, TEMPLATE_NAME, TEMPLATE_DESCRIPTION);

        assertEquals("template has been updated", result.getMessage());
        TemplateNameResponse template = result.getTemplate();
        assertNotNull(template);
        assertNotNull(template.getName());
        assertEquals(TEMPLATE_NAME, template.getName());
    }

    @Test
    void deleteTemplateSuccessTest() {
        TemplateStatusResponse result = mailgunTemplatesApi.deleteTemplate(MAIN_DOMAIN, TEMPLATE_NAME);

        assertEquals("template has been deleted", result.getMessage());
        TemplateNameResponse template = result.getTemplate();
        assertNotNull(template);
        assertNotNull(template.getName());

        FeignException exception = assertThrows(FeignException.class, () ->
                mailgunTemplatesApi.getSpecifiedVersionTemplateContent(MAIN_DOMAIN, TEMPLATE_NAME, TEMPLATE_VERSION_TAG_1)
        );

        assertTrue(exception.getMessage().contains("template not found"));
        assertEquals(404, exception.status());
    }

    @Test
    void getAllTemplateVersionsSuccessTest() {
        TemplateAllVersionsResponse result = mailgunTemplatesApi.getAllTemplateVersions(MAIN_DOMAIN, TEMPLATE_NAME);

        TemplateWithVersions templateWithVersions = result.getTemplate();
        assertNotNull(templateWithVersions);
        assertNotNull(templateWithVersions.getId());
        assertNotNull(templateWithVersions.getName());
        assertNotNull(templateWithVersions.getDescription());
        List<TemplateVersions> versions = templateWithVersions.getVersions();
        assertTrue(versions.size() > 1);
        TemplateVersions version = versions.get(0);
        assertNotNull(version.getId());
        assertNotNull(version.getActive());
        assertNotNull(version.getTag());
        assertEquals(TEMPLATE_ENGINE, version.getEngine());
        assertNotNull(version.getMjml());
        assertNotNull(version.getCreatedAt());
        assertNotNull(version.getComment());
        assertNotNull(result.getPaging());
    }

    @Test
    void getAllTemplateVersionsQueryOptionsSuccessTest() {
        PagingWithPivot pagingWithPivot = PagingWithPivot.builder()
                .limit(1)
                .page(Page.NEXT)
                .pivot(TEMPLATE_NAME)
                .build();

        TemplateAllVersionsResponse result = mailgunTemplatesApi.getAllTemplateVersions(MAIN_DOMAIN, TEMPLATE_NAME, pagingWithPivot);

        TemplateWithVersions templateWithVersions = result.getTemplate();
        List<TemplateVersions> versions = templateWithVersions.getVersions();
        assertEquals(1, versions.size());
    }

    @Test
    void createNewTemplateVersionSuccessTest() {
        TemplateVersionRequest request = TemplateVersionRequest.builder()
                .template(TEMPLATE_2)
                .tag(TEMPLATE_VERSION_TAG_2)
                .engine(TEMPLATE_ENGINE)
                .comment(TEMPLATE_COMMENT_UPDATED)
                .active(true)
                .build();

        TemplateWithMessageResponse result = mailgunTemplatesApi.createNewTemplateVersion(MAIN_DOMAIN, TEMPLATE_NAME, request);

        assertEquals("new version of the template has been stored", result.getMessage());
        TemplateWithVersion template = result.getTemplate();
        assertNotNull(template);
        assertNotNull(template.getId());
        assertEquals(TEMPLATE_NAME, template.getName());
        assertEquals(TEMPLATE_DESCRIPTION, template.getDescription());
        assertNotNull(template.getCreatedAt());
        TemplateVersion version = template.getVersion();
        assertNotNull(version.getId());
        assertEquals(true, version.getActive());
        assertEquals(TEMPLATE_VERSION_TAG_2, version.getTag());
        assertEquals(TEMPLATE_2, version.getTemplate());
        assertEquals(TEMPLATE_ENGINE, version.getEngine());
        assertTrue(StringUtils.isEmpty(version.getMjml()));
        assertNotNull(version.getCreatedAt());
        assertEquals(TEMPLATE_COMMENT_UPDATED, version.getComment());
    }

    @Test
    void updateSpecificTemplateVersionSuccessTest() {
        UpdateTemplateVersionRequest request = UpdateTemplateVersionRequest.builder()
                .template(TEMPLATE_2)
                .comment(TEMPLATE_COMMENT_UPDATED)
                .active(true)
                .build();

        TemplateVersionResponse result = mailgunTemplatesApi.updateSpecificTemplateVersion(MAIN_DOMAIN, TEMPLATE_NAME, TEMPLATE_VERSION_TAG_1, request);

        assertEquals("version has been updated", result.getMessage());
        TemplateVersionTag templateVersionTag = result.getTemplate();
        assertNotNull(templateVersionTag);
        VersionTag versionTag = templateVersionTag.getVersion();
        assertNotNull(versionTag);
        assertEquals(TEMPLATE_NAME, templateVersionTag.getName());
        assertEquals(TEMPLATE_VERSION_TAG_1, templateVersionTag.getName());
    }

    @Test
    void deleteSpecificTemplateVersionSuccessTest() {
        TemplateVersionResponse result = mailgunTemplatesApi.deleteSpecificTemplateVersion(MAIN_DOMAIN, TEMPLATE_NAME, TEMPLATE_VERSION_TAG_2);

        assertEquals("version has been deleted", result.getMessage());
        TemplateVersionTag templateVersionTag = result.getTemplate();
        assertNotNull(templateVersionTag);
        VersionTag versionTag = templateVersionTag.getVersion();
        assertNotNull(versionTag);
        assertEquals(TEMPLATE_NAME, templateVersionTag.getName());
        assertEquals(TEMPLATE_VERSION_TAG_2, versionTag.getTag());
    }

}
