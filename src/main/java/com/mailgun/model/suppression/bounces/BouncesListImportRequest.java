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
     */
    File file;

}

