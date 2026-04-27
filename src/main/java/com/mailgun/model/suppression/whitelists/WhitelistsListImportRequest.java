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
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/allowlist/post-v3--domainid--whitelists-import.md">Import allowlist</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class WhitelistsListImportRequest {

    /**
     * <p>
     * Import a CSV file containing addresses and/or domains to add to the allowlist.
     * </p>
     * <p>
     * CSV file must be 25MB or under. Column headers:
     * </p>
     * <pre>
     * <code>address</code> Email to allowlist (per row: either {@code address} or {@code domain}, not both)
     * <code>domain</code> Domain to allowlist (leave the other column blank on each row)
     * </pre>
     */
    File file;

}

