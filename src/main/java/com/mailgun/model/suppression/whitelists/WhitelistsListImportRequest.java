package com.mailgun.model.suppression.whitelists;

import java.io.File;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * <p>
 * Whitelists List Import Request.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-suppressions.html#import-a-list-of-addresses-and-or-domains">Import a list of whitelists</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class WhitelistsListImportRequest {

    /**
     * <p>
     * Import a CSV file containing a list of addresses and/or domains to add to the whitelist.
     * </p>
     * <p>
     * CSV file must be 25MB or under and must contain the following column headers:
     * </p>
     * <pre>
     * <code>address</code> Valid email address if you would like to whitelist email address
     * <code>domain</code> Valid domain name if you would like whitelist entire domain name
     * </pre>
     */
    File file;

}

