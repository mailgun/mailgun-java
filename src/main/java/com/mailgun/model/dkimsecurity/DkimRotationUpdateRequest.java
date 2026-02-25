package com.mailgun.model.dkimsecurity;

import feign.form.FormProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Request to update Automatic Sender Security DKIM key rotation for a domain (PUT /v1/dkim_management/domains/{name}/rotation).
 * Minimum allowed rotation interval is 5 days (e.g. "5d").
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dkim-security/put-v1-dkim-management-domains--name--rotation">Update Automatic Sender Security DKIM key rotation</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class DkimRotationUpdateRequest {

    /**
     * If true, enables DKIM Auto-Rotation; if false, disables it. Required.
     */
    @FormProperty("rotation_enabled")
    Boolean rotationEnabled;

    /**
     * Interval at which to rotate keys (e.g. "5d" for five days). Optional when disabling; minimum 5 days when enabling.
     */
    @FormProperty("rotation_interval")
    String rotationInterval;
}
