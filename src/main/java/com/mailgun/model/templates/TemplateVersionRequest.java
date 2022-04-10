package com.mailgun.model.templates;

import com.mailgun.enums.YesNo;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * <p>
 * Template new version request.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-templates.html">Templates</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class TemplateVersionRequest {

    /**
     * <p>
     * Content of the template.
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
     * (Optional) Template engine.
     * </p>
     * <p>
     * The only engine currently supported is <code>handlebars</code>.
     * </p>
     */
    String engine;

    /**
     * <p>
     * (Optional) Comments relating to the stored version.
     * </p>
     */
    String comment;

    /**
     * <p>
     * (Optional) If this flag is set to <code>YES</code>, this version becomes active.
     * </p>
     */
    String active;

    public static class TemplateVersionRequestBuilder {

        /**
         * <p>
         * (Optional) If this flag is set to <code>TRUE</code>, this version becomes active.
         * <p>
         *
         * @param active flag whether the version is active
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public TemplateVersionRequest.TemplateVersionRequestBuilder active(boolean active) {
            if (active) {
                this.active = YesNo.YES.getValue();
            }
            return this;
        }
    }

}
