package com.mailgun.model.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * <p>
 * Receiving DNS record.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-domains.html#domains">Domains</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReceivingDnsRecords {

    List<String> cached;

    /**
     * <p>
     * Record priority.
     * </p>
     */
    String priority;

    /**
     * <p>
     * Record type.
     * </p>
     */
    @JsonProperty("record_type")
    String recordType;

    /**
     * <p>
     * Record status.
     * </p>
     */
    String valid;

    /**
     * <p>
     * Record value.
     * </p>
     */
    String value;

}
