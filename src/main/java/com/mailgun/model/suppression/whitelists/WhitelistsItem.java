package com.mailgun.model.suppression.whitelists;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mailgun.enums.WhitelistType;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;

import static com.mailgun.util.Constants.RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NAME;

/**
 * <p>
 * Whitelists item.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-suppressions.html#whitelists">Suppressions/Whitelists</a>
 */
@Value
@Jacksonized
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WhitelistsItem {

    /**
     * <p>
     * Address or domain.
     * </p>
     */
    String value;

    /**
     * <p>
     * Reason for white listing, why the record was created.
     * </p>
     */
    String reason;

    /**
     * <p>
     * Whitelist type.
     * </p>
     * {@link WhitelistType}
     */
    WhitelistType type;

    /**
     * <p>
     * Timestamp of an whitelist event.
     * </p>
     * {@link ZonedDateTime}
     */
    @JsonFormat(pattern = RFC_2822_DATE_TIME_PATTERN_TIME_ZONE_NAME)
    ZonedDateTime createdAt;

}
