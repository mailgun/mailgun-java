package com.mailgun.model.domainkeys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mailgun.model.domains.SendingDnsRecord;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Response after updating DKIM authority (PUT /v3/domains/{name}/dkim_authority).
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domain-keys/put-v3-domains--name--dkim-authority">Update DKIM authority</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DkimAuthorityResponse {

    String message;

    @JsonProperty("sending_dns_records")
    List<SendingDnsRecord> sendingDnsRecords;

    Boolean changed;
}
