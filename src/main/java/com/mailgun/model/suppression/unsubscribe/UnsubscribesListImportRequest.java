package com.mailgun.model.suppression.unsubscribe;

import java.io.File;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * <p>
 * Unsubscribes List Import Request.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-suppressions.html#import-a-list-of-unsubscribes">Import a list of unsubscribes</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class UnsubscribesListImportRequest {

    /**
     * <p>
     * An uploaded CSV file containing a list of addresses to add to the unsubscribe list.
     * </p>
     * <p>
     * CSV file must be 25MB or under and must contain the following column headers:
     * </p>
     * <pre>
     * <code>address</code> Valid email address
     * <code>tags</code> Tag to unsubscribe from, use * to unsubscribe an address from all domain’s correspondence (optional, default: *)
     * <code>created_at</code> Timestamp of a bounce event in RFC2822 format (optional, default: current time)
     * </pre>
     */
    File file;

}