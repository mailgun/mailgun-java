package com.mailgun.model.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * <p>
 * List of domains.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-domains.html#domains">Domains</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DomainListResponse {

    /**
     * <p>
     * List of domains.
     * </p>
     * {@link Domain}
     */
    List<Domain> items;

    /**
     * <p>
     * The total number of domains.
     * </p>
     */
    @JsonProperty("total_count")
    Integer totalCount;

}
