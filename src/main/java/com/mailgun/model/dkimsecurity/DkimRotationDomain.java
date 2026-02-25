package com.mailgun.model.dkimsecurity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Domain object in the DKIM rotation update response (PUT /v1/dkim_management/domains/{name}/rotation).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/dkim-security/put-v1-dkim-management-domains--name--rotation">Update Automatic Sender Security DKIM key rotation</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DkimRotationDomain {

    String id;

    @JsonProperty("account_id")
    String accountId;

    String sid;
    String name;
    String state;

    @JsonProperty("active_selector")
    String activeSelector;

    @JsonProperty("rotation_enabled")
    String rotationEnabled;

    @JsonProperty("rotation_interval")
    String rotationInterval;

    List<DkimRotationRecord> records;
}
