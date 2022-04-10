package com.mailgun.model.suppression.whitelists;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * <p>
 * Whitelists request.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-suppressions.html#whitelists">Suppressions/Whitelists</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class WhitelistsRequest {

    /**
     * <p>
     * Valid email address if you would like to whitelist email address.
     * </p>
     * Note: The single request accepts either one address or domain parameter
     */
    String address;

    /**
     * <p>
     * Valid domain name if you would like whitelist entire domain name.
     * </p>
     * Note: The single request accepts either one address or domain parameter
     */
    String domain;

    /**
     * <p>
     * Reason for whitelisting.
     * </p>
     */
    String reason;

}
