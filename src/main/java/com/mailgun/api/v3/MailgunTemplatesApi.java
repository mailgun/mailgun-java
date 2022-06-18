package com.mailgun.api.v3;

import com.mailgun.api.MailgunApi;
import com.mailgun.model.PagingWithPivot;
import com.mailgun.model.ResponseWithMessage;
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
 * <p>
 * Templates Api.
 * </p>
 * <p>
 * This API allows you to store predefined templates and use them to send messages using the Sending API.
 * </p>
 * <p>
 * The API has the following limitations:
 * </p>
 * <pre>
 * 100 templates per domain
 * 10 versions per template
 * 100Kb max template size
 * </pre>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-templates.html">Templates</a>
 */
@Headers("Accept: application/json")
public interface MailgunTemplatesApi extends MailgunApi {

    /**
     * <p>
     * Returns a list of stored templates for the domain (limit to 10 entries).
     * </p>
     *
     * @param domain Name of the domain
     * @return {@link TemplatesResult}
     */
    @RequestLine("GET /{domain}/templates")
    TemplatesResult getAllTemplates(@Param("domain") String domain);

    /**
     * <p>
     * Returns a list of stored templates for the domain (limit to 10 entries).
     * </p>
     *
     * @param domain Name of the domain
     * @return {@link Response}
     */
    @RequestLine("GET /{domain}/templates")
    Response getAllTemplatesFeignResponse(@Param("domain") String domain);

    /**
     * <p>
     * Returns a list of stored templates for the domain with paging.
     * </p>
     *
     * @param domain       Name of the domain
     * @param queryOptions {@link PagingWithPivot}
     * @return {@link TemplatesResult}
     */
    @RequestLine("GET /{domain}/templates")
    TemplatesResult getAllTemplates(@Param("domain") String domain, @QueryMap PagingWithPivot queryOptions);

    /**
     * <p>
     * Returns a list of stored templates for the domain with paging.
     * </p>
     *
     * @param domain       Name of the domain
     * @param queryOptions {@link PagingWithPivot}
     * @return {@link Response}
     */
    @RequestLine("GET /{domain}/templates")
    Response getAllTemplatesFeignResponse(@Param("domain") String domain, @QueryMap PagingWithPivot queryOptions);

    /**
     * <p>
     * Returns metadata information about a stored template.
     * </p>
     *
     * @param domain Name of the domain
     * @param name   Name of the template
     * @return {@link TemplateResponse}
     */
    @RequestLine("GET /{domain}/templates/{name}")
    TemplateResponse getTemplate(@Param("domain") String domain, @Param("name") String name);

    /**
     * <p>
     * Returns metadata information about a stored template.
     * </p>
     *
     * @param domain Name of the domain
     * @param name   Name of the template
     * @return {@link Response}
     */
    @RequestLine("GET /{domain}/templates/{name}")
    Response getTemplateFeignResponse(@Param("domain") String domain, @Param("name") String name);

    /**
     * <p>
     * Returns the content of the active version of the template with metadata information.
     * </p>
     *
     * @param domain Name of the domain
     * @param name   Name of the template
     * @return {@link TemplateWithVersionResponse}
     */
    @RequestLine("GET /{domain}/templates/{name}?active=yes")
    TemplateWithVersionResponse getActiveTemplateVersionContent(@Param("domain") String domain, @Param("name") String name);

    /**
     * <p>
     * Returns the content of the active version of the template with metadata information.
     * </p>
     *
     * @param domain Name of the domain
     * @param name   Name of the template
     * @return {@link Response}
     */
    @RequestLine("GET /{domain}/templates/{name}?active=yes")
    Response getActiveTemplateVersionContentFeignResponse(@Param("domain") String domain, @Param("name") String name);

    /**
     * <p>
     * This API stores a new template, including its name, description and (optionally) the template content.
     * </p>
     * <p>
     * If the template content is provided, a new version is automatically created and becomes the active version.
     * </p>
     *
     * @param domain  Name of the domain
     * @param request {@link TemplateRequest}
     * @return {@link TemplateWithMessageResponse}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /{domain}/templates")
    TemplateWithMessageResponse storeNewTemplate(@Param("domain") String domain, TemplateRequest request);

    /**
     * <p>
     * This API stores a new template, including its name, description and (optionally) the template content.
     * </p>
     * <p>
     * If the template content is provided, a new version is automatically created and becomes the active version.
     * </p>
     *
     * @param domain  Name of the domain
     * @param request {@link TemplateRequest}
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /{domain}/templates")
    Response storeNewTemplateFeignResponse(@Param("domain") String domain, TemplateRequest request);

    /**
     * <p>
     * Update the metadata information of the template.
     * </p>
     *
     * @param domain      Name of the domain
     * @param name        Name of the template
     * @param description Updated description of the template
     * @return {@link TemplateStatusResponse}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /{domain}/templates/{name}")
    TemplateStatusResponse updateTemplate(@Param("domain") String domain, @Param("name") String name, @Param("description") String description);

    /**
     * <p>
     * Update the metadata information of the template.
     * </p>
     *
     * @param domain      Name of the domain
     * @param name        Name of the template
     * @param description Updated description of the template
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /{domain}/templates/{name}")
    Response updateTemplateFeignResponse(@Param("domain") String domain, @Param("name") String name, @Param("description") String description);

    /**
     * <p>
     * Delete the template.
     * </p>
     * NOTE: This method deletes all versions of the specified template.
     *
     * @param domain Name of the domain
     * @param name   Name of the template
     * @return {@link TemplateStatusResponse}
     */
    @RequestLine("DELETE /{domain}/templates/{name}")
    TemplateStatusResponse deleteTemplate(@Param("domain") String domain, @Param("name") String name);

    /**
     * <p>
     * Delete the template.
     * </p>
     * NOTE: This method deletes all versions of the specified template.
     *
     * @param domain Name of the domain
     * @param name   Name of the template
     * @return {@link Response}
     */
    @RequestLine("DELETE /{domain}/templates/{name}")
    Response deleteTemplateFeignResponse(@Param("domain") String domain, @Param("name") String name);

    /**
     * <p>
     * Delete all stored templates for the domain.
     * </p>
     *
     * @param domain Name of the domain
     * @return {@link ResponseWithMessage}
     */
    @RequestLine("DELETE /{domain}/templates")
    ResponseWithMessage deleteAllTemplatesInDomain(@Param("domain") String domain);

    /**
     * <p>
     * Delete all stored templates for the domain.
     * </p>
     *
     * @param domain Name of the domain
     * @return {@link Response}
     */
    @RequestLine("DELETE /{domain}/templates")
    Response deleteAllTemplatesInDomainFeignResponse(@Param("domain") String domain);

    /**
     * <p>
     * Returns a list of stored versions of the template (limit to 10 entries).
     * </p>
     *
     * @param domain       Name of the domain
     * @param templateName Name of the template
     * @return {@link TemplateAllVersionsResponse}
     */
    @RequestLine("GET /{domain}/templates/{templateName}/versions")
    TemplateAllVersionsResponse getAllTemplateVersions(@Param("domain") String domain, @Param("templateName") String templateName);

    /**
     * <p>
     * Returns a list of stored versions of the template (limit to 10 entries).
     * </p>
     *
     * @param domain       Name of the domain
     * @param templateName Name of the template
     * @return {@link Response}
     */
    @RequestLine("GET /{domain}/templates/{templateName}/versions")
    Response getAllTemplateVersionsFeignResponse(@Param("domain") String domain, @Param("templateName") String templateName);

    /**
     * <p>
     * Returns a list of stored versions of the template.
     * </p>
     *
     * @param domain       Name of the domain
     * @param templateName Name of the template
     * @param queryOptions {@link PagingWithPivot}
     * @return {@link TemplateAllVersionsResponse}
     */
    @RequestLine("GET /{domain}/templates/{templateName}/versions")
    TemplateAllVersionsResponse getAllTemplateVersions(@Param("domain") String domain, @Param("templateName") String templateName, @QueryMap PagingWithPivot queryOptions);

    /**
     * <p>
     * Returns a list of stored versions of the template.
     * </p>
     *
     * @param domain       Name of the domain
     * @param templateName Name of the template
     * @param queryOptions {@link PagingWithPivot}
     * @return {@link Response}
     */
    @RequestLine("GET /{domain}/templates/{templateName}/versions")
    Response getAllTemplateVersionsFeignResponse(@Param("domain") String domain, @Param("templateName") String templateName, @QueryMap PagingWithPivot queryOptions);

    /**
     * <p>
     * Retrieve information and content of specified version of the template.
     * </p>
     *
     * @param domain       Name of the domain
     * @param templateName Name of the template
     * @param tag          Tag of the template version
     * @return {@link TemplateWithVersionResponse}
     */
    @RequestLine("GET /{domain}/templates/{templateName}/versions/{tag}")
    TemplateWithVersionResponse getSpecifiedVersionTemplateContent(@Param("domain") String domain, @Param("templateName") String templateName, @Param("tag") String tag);

    /**
     * <p>
     * Retrieve information and content of specified version of the template.
     * </p>
     *
     * @param domain       Name of the domain
     * @param templateName Name of the template
     * @param tag          Tag of the template version
     * @return {@link Response}
     */
    @RequestLine("GET /{domain}/templates/{templateName}/versions/{tag}")
    Response getSpecifiedVersionTemplateContentFeignResponse(@Param("domain") String domain, @Param("templateName") String templateName, @Param("tag") String tag);

    /**
     * <p>
     * Create a new version of a template. If the template does not contain any other versions, the first version becomes active.
     * </p>
     *
     * @param domain       Name of the domain
     * @param templateName Name of the template
     * @param request      {@link TemplateVersionRequest}
     * @return {@link TemplateWithMessageResponse}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /{domain}/templates/{templateName}/versions")
    TemplateWithMessageResponse createNewTemplateVersion(@Param("domain") String domain, @Param("templateName") String templateName, TemplateVersionRequest request);

    /**
     * <p>
     * Create a new version of a template. If the template does not contain any other versions, the first version becomes active.
     * </p>
     *
     * @param domain       Name of the domain
     * @param templateName Name of the template
     * @param request      {@link TemplateVersionRequest}
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("POST /{domain}/templates/{templateName}/versions")
    Response createNewTemplateVersionFeignResponse(@Param("domain") String domain, @Param("templateName") String templateName, TemplateVersionRequest request);

    /**
     * <p>
     * Update information or content of the specific version of the template.
     * </p>
     *
     * @param domain       Name of the domain
     * @param templateName Name of the template
     * @param tag          Tag of the template version
     * @param request      {@link UpdateTemplateVersionRequest}
     * @return {@link TemplateVersionResponse}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /{domain}/templates/{templateName}/versions/{tag}")
    TemplateVersionResponse updateSpecificTemplateVersion(@Param("domain") String domain, @Param("templateName") String templateName,
                                                          @Param("tag") String tag, UpdateTemplateVersionRequest request);

    /**
     * <p>
     * Update information or content of the specific version of the template.
     * </p>
     *
     * @param domain       Name of the domain
     * @param templateName Name of the template
     * @param tag          Tag of the template version
     * @param request      {@link UpdateTemplateVersionRequest}
     * @return {@link Response}
     */
    @Headers("Content-Type: multipart/form-data")
    @RequestLine("PUT /{domain}/templates/{templateName}/versions/{tag}")
    Response updateSpecificTemplateVersionFeignResponse(@Param("domain") String domain, @Param("templateName") String templateName,
                                                        @Param("tag") String tag, UpdateTemplateVersionRequest request);

    /**
     * Delete a specific version of the template.
     *
     * @param domain          Name of the domain
     * @param templateName    Name of the template
     * @param templateVersion Template version tag
     * @return {@link TemplateVersionResponse}
     */
    @RequestLine("DELETE /{domain}/templates/{templateName}/versions/{templateVersion}")
    TemplateVersionResponse deleteSpecificTemplateVersion(@Param("domain") String domain, @Param("templateName") String templateName, @Param("templateVersion") String templateVersion);

    /**
     * Delete a specific version of the template.
     *
     * @param domain          Name of the domain
     * @param templateName    Name of the template
     * @param templateVersion Template version tag
     * @return {@link Response}
     */
    @RequestLine("DELETE /{domain}/templates/{templateName}/versions/{templateVersion}")
    Response deleteSpecificTemplateVersionFeignResponse(@Param("domain") String domain, @Param("templateName") String templateName, @Param("templateVersion") String templateVersion);

}
