package com.mailgun.model.suppression.bounces;

import java.io.File;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * <p>
 * Complaints List Import Request.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-suppressions.html#import-a-list-of-complaints">Import a list of complaints</a>
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/complaints/post-v3--domainid--complaints-import.md">Import complaint list</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class ComplaintsListImportRequest {

    /**
     * <p>
     * An uploaded CSV file containing a list of addresses to add to the complaint list.
     * </p>
     * <p>
     * CSV file must be 25MB or under and must contain the following column headers:
     * </p>
     * <pre>
     * <code>address</code> Valid email address
     * <code>created_at</code> Timestamp of the complaint event in RFC2822 format (optional, default: current time)
     * </pre>
     */
    File file;

}

