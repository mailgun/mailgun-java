package com.mailgun.model.templates;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;

import static com.mailgun.util.Constants.RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NAME;

/**
 * <p>
 * Template version.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-templates.html">Templates</a>
 */
@Value
@Jacksonized
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TemplateVersion {

    /**
     * <p>
     * Template version id.
     * </p>
     */
    String id;

    /**
     * <p>
     * Flag whether the version is active.
     * </p>
     */
    Boolean active;

    /**
     * <p>
     * The tag of the template version.
     * </p>
     */
    String tag;

    /**
     * <p>
     * Content of the template.
     * </p>
     */
    String template;

    /**
     * <p>
     * The template engine to use to render the template.
     * </p>
     */
    String engine;


    String mjml;

    /**
     * <p>
     * Template version creation time.
     * </p>
     * {@link ZonedDateTime}
     */
    @JsonFormat(pattern = RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NAME)
    ZonedDateTime createdAt;

    /**
     * <p>
     * (Optional) Version comment.
     * </p>
     */
    String comment;

}
