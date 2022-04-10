package com.mailgun.model.suppression.unsubscribe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mailgun.model.Paging;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * <p>
 * Unsubscribe item response.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-suppressions.html#unsubscribes">Suppressions/Unsubscribe</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UnsubscribeItemResponse {


    /**
     * <p>
     * List of {@link UnsubscribeItem}.
     * </p>
     */
    List<UnsubscribeItem> items;

    /**
     * <p>
     * Provides pagination urls.
     * </p>
     * {@link Paging}
     */
    Paging paging;

}
