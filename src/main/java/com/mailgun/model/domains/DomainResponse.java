package com.mailgun.model.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Single domain information, including credentials and DNS records.
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-domains.html#domains">Domains</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DomainResponse {

    /**
     * <p>
     * Domain
     * </p>
     * {@link Domain}
     */
    Domain domain;

    /**
     * <p>
     * Result status message.
     * </p>
     */
    String message;

    /**
     * <p>
     * receivingDnsRecords.
     * </p>
     */
    @JsonProperty("receiving_dns_records")
    List<ReceivingDnsRecords> receivingDnsRecords;

    /**
     * <p>
     * Sending DNS records.
     * </p>
     */
    @JsonProperty("sending_dns_records")
    List<SendingDnsRecord> sendingDnsRecords;

}
