package com.mailgun.model.dkimsecurity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Response after updating Automatic Sender Security DKIM key rotation (PUT /v1/dkim_management/domains/{name}/rotation).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dkim-security/put-v1-dkim-management-domains--name--rotation">Update Automatic Sender Security DKIM key rotation</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DkimRotationUpdateResponse {

    DkimRotationDomain domain;
}
