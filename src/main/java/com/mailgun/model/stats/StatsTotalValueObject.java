package com.mailgun.model.stats;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatsTotalValueObject {

    /**
     * <p>
     * Total.
     * </p>
     */
    Integer total;

}
