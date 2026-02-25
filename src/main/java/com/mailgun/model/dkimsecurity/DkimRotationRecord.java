package com.mailgun.model.dkimsecurity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * A single DNS record in the DKIM rotation domain response (e.g. CNAME for verification).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dkim-security/put-v1-dkim-management-domains--name--rotation">Update Automatic Sender Security DKIM key rotation</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DkimRotationRecord {

    String type;
    String identifier;
    String value;
    String comment;
}
