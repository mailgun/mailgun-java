package com.mailgun.model.templates;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * JSON body for template copy ({@code application/json}):
 * {@code PUT /v4/templates/{template_name}/copy} or {@code PUT /v3/{domain_name}/templates/{template_name}/copy}.
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CopyTemplateRequest {

    @Singular
    List<CopyTemplateItem> requests;

    /**
     * Version tags to copy; omit or leave empty to copy all versions.
     */
    @JsonProperty("source_versions")
    List<String> sourceVersions;

}
