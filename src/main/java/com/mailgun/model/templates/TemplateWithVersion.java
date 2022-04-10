package com.mailgun.model.templates;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;

import static com.mailgun.util.Constants.RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NAME;

/**
 * <p>
 * Template with version.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-templates.html">Templates</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class TemplateWithVersion {

    /**
     * <p>
     * Template id.
     * </p>
     */
    String id;

    /**
     * <p>
     * Name of the template.
     * </p>
     */
    String name;

    /**
     * <p>
     * Template description.
     * </p>
     */
    String description;

    /**
     * <p>
     * Template creation time.
     * </p>
     * {@link ZonedDateTime}
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NAME)
    ZonedDateTime createdAt;

    String createdBy;

    /**
     * <p>
     * {@link TemplateVersion}.
     * </p>
     */
    TemplateVersion version;

}
