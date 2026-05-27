package com.mailgun.model.templates;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.Collections;
import java.util.List;

/**
 * Response for template copy (HTTP 200).
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CopyTemplateResponse {

    String message;

    @JsonProperty("failed_copies")
    @Builder.Default
    List<CopyTemplateFailure> failedCopies = Collections.emptyList();

}
