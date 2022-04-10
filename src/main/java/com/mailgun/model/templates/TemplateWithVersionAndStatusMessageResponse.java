package com.mailgun.model.templates;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Response with a template, version, and status message.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-templates.html">Templates</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class TemplateWithVersionAndStatusMessageResponse {

    /**
     * <p>
     * Result status message.
     * </p>
     */
    String message;


    /**
     * <p>
     * {@link TemplateWithVersion}.
     * </p>
     */
    TemplateWithVersion template;

}
