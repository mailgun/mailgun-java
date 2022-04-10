package com.mailgun.model.templates;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * <p>
 * Template request.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-templates.html">Templates</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class TemplateRequest {

    /**
     * <p>
     * Name of the template being created.
     * </p>
     * <p>
     * The name can contain alpha characters, digits and next symbols: <code>.-_~</code>
     * </p>
     */
    String name;

    /**
     * <p>
     * Description of the template being created.
     * </p>
     */
    String description;

    /**
     * <p>
     * (Optional) Content of the template.
     * </p>
     */
    String template;

    /**
     * <p>
     * (Optional) Initial tag of the created version. If template parameter is provided and tag is missing default value initial is used.
     * </p>
     * <p>
     * The tag can contain alpha characters, digits and next symbols: <code>.-_~</code>
     * </p>
     */
    String tag;

    /**
     * <p>
     * (Optional) The template engine to use to render the template.
     * Valid only if template parameter is provided.
     * </p>
     * <p>
     * Currently the API supports only one engine: <code>handlebars</code>.
     * </p>
     */
    String engine;

    /**
     * <p>
     * (Optional) Version comment.
     * Valid only if new version is being created (template parameter is provided);
     * </p>
     */
    String comment;

}
