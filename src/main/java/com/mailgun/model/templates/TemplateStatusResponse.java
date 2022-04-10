package com.mailgun.model.templates;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Template status response.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-templates.html">Templates</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class TemplateStatusResponse {

    /**
     * <p>
     * Result status message.
     * </p>
     */
    String message;

    /**
     * <p>
     * {@link TemplateNameResponse}
     * </p>
     */
    TemplateNameResponse template;

}
