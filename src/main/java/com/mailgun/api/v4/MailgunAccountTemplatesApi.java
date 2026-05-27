package com.mailgun.api.v4;

import com.mailgun.api.MailgunApi;
import com.mailgun.enums.ApiVersion;
import com.mailgun.model.PagingWithPivot;
import com.mailgun.model.ResponseWithMessage;
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
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import feign.Response;

/**
 * Account Templates API (v4): templates shared across all domains on the account ({@code /v4/templates}).
 * <p>
 * For per-domain templates use {@link com.mailgun.api.v3.MailgunTemplatesApi} ({@code /v3/{domain}/templates}).
 * Request/response models are shared where the API shape matches; copy uses {@link CopyTemplateRequest}.
 * </p>
 * <p>
 * Limits: 100 templates per account, 40 versions per template, 100 KB max template size.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/account-templates">Account Templates</a>
 */
@Headers("Accept: application/json")
public interface MailgunAccountTemplatesApi extends MailgunApi {

    static ApiVersion getApiVersion() {
        return ApiVersion.V_4;
    }

    /**
     * {@code GET /v4/templates}: list account-level templates.
     */
    @RequestLine("GET /templates")
    TemplatesResult getAllTemplates();

    /**
     * {@code GET /v4/templates} (raw {@link Response}).
     */
    @RequestLine("GET /templates")
    Response getAllTemplatesFeignResponse();

    /**
     * {@code GET /v4/templates} with paging.
     */
    @RequestLine("GET /templates")
    TemplatesResult getAllTemplates(@QueryMap PagingWithPivot queryOptions);

    @RequestLine("GET /templates")
    Response getAllTemplatesFeignResponse(@QueryMap PagingWithPivot queryOptions);

    /**
     * {@code GET /v4/templates/{template_name}}: template metadata; use {@link #getActiveTemplateVersionContent(String)} for active content.
     */
    @RequestLine("GET /templates/{templateName}")
    TemplateResponse getTemplate(@Param("templateName") String templateName);

    @RequestLine("GET /templates/{templateName}")
    Response getTemplateFeignResponse(@Param("templateName") String templateName);

    /**
     * {@code GET /v4/templates/{template_name}?active=yes}: active version content and metadata.
     */
    @RequestLine("GET /templates/{templateName}?active=yes")
    TemplateWithVersionResponse getActiveTemplateVersionContent(@Param("templateName") String templateName);

    @RequestLine("GET /templates/{templateName}?active=yes")
    Response getActiveTemplateVersionContentFeignResponse(@Param("templateName") String templateName);

    /**
     * {@code POST /v4/templates}: create an account-level template (optional initial version from {@code template} field).
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /templates")
    TemplateWithMessageResponse storeNewTemplate(TemplateRequest request);

    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /templates")
    Response storeNewTemplateFeignResponse(TemplateRequest request);

    /**
     * {@code PUT /v4/templates/{template_name}}: update template description.
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /templates/{templateName}")
    TemplateStatusResponse updateTemplate(@Param("templateName") String templateName, @Param("description") String description);

    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /templates/{templateName}")
    Response updateTemplateFeignResponse(@Param("templateName") String templateName, @Param("description") String description);

    /**
     * {@code DELETE /v4/templates/{template_name}}: delete template and all versions.
     */
    @RequestLine("DELETE /templates/{templateName}")
    TemplateStatusResponse deleteTemplate(@Param("templateName") String templateName);

    @RequestLine("DELETE /templates/{templateName}")
    Response deleteTemplateFeignResponse(@Param("templateName") String templateName);

    /**
     * {@code DELETE /v4/templates}: delete all account-level templates and versions.
     * Domain equivalent: {@link com.mailgun.api.v3.MailgunTemplatesApi#deleteAllTemplatesInDomain(String)}.
     */
    @RequestLine("DELETE /templates")
    ResponseWithMessage deleteAllTemplates();

    @RequestLine("DELETE /templates")
    Response deleteAllTemplatesFeignResponse();

    /**
     * {@code GET /v4/templates/{template_name}/versions}: paginated template versions.
     */
    @RequestLine("GET /templates/{templateName}/versions")
    TemplateAllVersionsResponse getAllTemplateVersions(@Param("templateName") String templateName);

    @RequestLine("GET /templates/{templateName}/versions")
    Response getAllTemplateVersionsFeignResponse(@Param("templateName") String templateName);

    @RequestLine("GET /templates/{templateName}/versions")
    TemplateAllVersionsResponse getAllTemplateVersions(@Param("templateName") String templateName, @QueryMap PagingWithPivot queryOptions);

    @RequestLine("GET /templates/{templateName}/versions")
    Response getAllTemplateVersionsFeignResponse(@Param("templateName") String templateName, @QueryMap PagingWithPivot queryOptions);

    /**
     * {@code GET /v4/templates/{template_name}/versions/{version_name}}.
     */
    @RequestLine("GET /templates/{templateName}/versions/{versionName}")
    TemplateWithVersionResponse getSpecifiedVersionTemplateContent(@Param("templateName") String templateName,
                                                                   @Param("versionName") String versionName);

    @RequestLine("GET /templates/{templateName}/versions/{versionName}")
    Response getSpecifiedVersionTemplateContentFeignResponse(@Param("templateName") String templateName,
                                                             @Param("versionName") String versionName);

    /**
     * {@code POST /v4/templates/{template_name}/versions}: add a version (up to 40 per template).
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /templates/{templateName}/versions")
    TemplateWithMessageResponse createNewTemplateVersion(@Param("templateName") String templateName, TemplateVersionRequest request);

    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /templates/{templateName}/versions")
    Response createNewTemplateVersionFeignResponse(@Param("templateName") String templateName, TemplateVersionRequest request);

    /**
     * {@code PUT /v4/templates/{template_name}/versions/{version_name}}.
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /templates/{templateName}/versions/{versionName}")
    TemplateVersionResponse updateSpecificTemplateVersion(@Param("templateName") String templateName,
                                                          @Param("versionName") String versionName,
                                                          UpdateTemplateVersionRequest request);

    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /templates/{templateName}/versions/{versionName}")
    Response updateSpecificTemplateVersionFeignResponse(@Param("templateName") String templateName,
                                                        @Param("versionName") String versionName,
                                                        UpdateTemplateVersionRequest request);

    /**
     * {@code DELETE /v4/templates/{template_name}/versions/{version_name}}.
     */
    @RequestLine("DELETE /templates/{templateName}/versions/{versionName}")
    TemplateVersionResponse deleteSpecificTemplateVersion(@Param("templateName") String templateName,
                                                          @Param("versionName") String versionName);

    @RequestLine("DELETE /templates/{templateName}/versions/{versionName}")
    Response deleteSpecificTemplateVersionFeignResponse(@Param("templateName") String templateName,
                                                        @Param("versionName") String versionName);

    /**
     * {@code PUT /v4/templates/{template_name}/versions/{version_name}/copy/{new_version_name}}.
     */
    @RequestLine("PUT /templates/{templateName}/versions/{versionName}/copy/{newVersionName}")
    TemplateWithMessageResponse copyTemplateVersion(@Param("templateName") String templateName,
                                                    @Param("versionName") String versionName,
                                                    @Param("newVersionName") String newVersionName);

    @RequestLine("PUT /templates/{templateName}/versions/{versionName}/copy/{newVersionName}")
    Response copyTemplateVersionFeignResponse(@Param("templateName") String templateName,
                                              @Param("versionName") String versionName,
                                              @Param("newVersionName") String newVersionName);

    /**
     * {@code PUT /v4/templates/{template_name}/copy}: copy into other accounts ({@code application/json}).
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @RequestLine("PUT /templates/{templateName}/copy")
    CopyTemplateResponse copyTemplate(@Param("templateName") String templateName, CopyTemplateRequest request);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @RequestLine("PUT /templates/{templateName}/copy")
    Response copyTemplateFeignResponse(@Param("templateName") String templateName, CopyTemplateRequest request);

    /**
     * {@code PUT /v4/templates/{template_name}/rename/{new_template_name}}.
     */
    @RequestLine("PUT /templates/{templateName}/rename/{newTemplateName}")
    TemplateWithMessageResponse renameTemplate(@Param("templateName") String templateName,
                                               @Param("newTemplateName") String newTemplateName);

    @RequestLine("PUT /templates/{templateName}/rename/{newTemplateName}")
    Response renameTemplateFeignResponse(@Param("templateName") String templateName,
                                         @Param("newTemplateName") String newTemplateName);

}
