package com.mailgun.model.domains;

import feign.form.FormProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Request to update open tracking settings (PUT /v3/domains/{name}/tracking/open).
 * API expects active as boolean. Omit a field to leave current setting unchanged.
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-tracking/put-v3-domains--name--tracking-open">Update open tracking settings</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class OpenTrackingSettingsRequest {

    /**
     * <p>
     * Enable or disable open tracking. Omit to keep current setting.
     * </p>
     */
    Boolean active;

    /**
     * <p>
     * If true, place the tracking pixel at the top of the HTML body. Omit to keep current setting.
     * </p>
     */
    @FormProperty("place_at_the_top")
    Boolean placeAtTheTop;

}
