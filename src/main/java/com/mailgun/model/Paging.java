package com.mailgun.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;


/**
 * <p>
 * Provides pagination urls.
 * </p>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Paging {

    /**
     * <p>
     * URL for the first page.
     * </p>
     */
    String first;

    /**
     * <p>
     * URL for the last page.
     * </p>
     */
    String last;

    /**
     * <p>
     * URL for the next page.
     * </p>
     */
    String next;

    /**
     * <p>
     * URL for the previous page.
     * </p>
     */
    String previous;

}
