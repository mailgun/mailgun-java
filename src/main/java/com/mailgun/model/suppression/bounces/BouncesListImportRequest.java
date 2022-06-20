package com.mailgun.model.suppression.bounces;

import java.io.File;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * <p>
 * Bounces List Import Request.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-suppressions.html#import-a-list-of-bounces">Import a list of bounces</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class BouncesListImportRequest {

    /**
     * <p>
     * An uploaded CSV file containing a list of addresses to add to the bounce list.
     * </p>
     * <p>
     * CSV file must be 25MB or under and must contain the following column headers:
     * </p>
     * <pre>
     * <code>address</code> Valid email address
     * <code>code</code> Error code (optional, default: 550)
     * <code>error</code> Error description (optional, default: empty string)
     * <code>created_at</code> Timestamp of a bounce event in RFC2822 format (optional, default: current time)
     * </pre>
     */
    File file;

}

