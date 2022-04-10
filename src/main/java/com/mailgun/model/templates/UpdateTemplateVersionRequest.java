package com.mailgun.model.templates;

import com.mailgun.enums.YesNo;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * <p>
 * Request to update template version.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-templates.html">Templates</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class UpdateTemplateVersionRequest {

    /**
     * <p>
     * (Optional) The new content of the version.
     * </p>
     */
    String template;

    /**
     * <p>
     * (Optional) New comment for the provided version.
     * </p>
     */
    String comment;

    /**
     * <p>
     * (Optional) If this flag is set to <code>YES</code>, this version becomes active.
     * </p>
     */
    String active;

    public static class UpdateTemplateVersionRequestBuilder {

        /**
         * <p>
         * (Optional) If this flag is set to <code>TRUE</code>, this version becomes active.
         * <p>
         *
         * @param active flag whether the version is active
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public UpdateTemplateVersionRequest.UpdateTemplateVersionRequestBuilder active(boolean active) {
            if (active) {
                this.active = YesNo.YES.getValue();
            }
            return this;
        }
    }

}
