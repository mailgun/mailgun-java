package com.mailgun.model.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.ToString;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Storage.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-events.html">Events API</a>
 */
@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Storage {

    /**
     * <p>
     * Storage url.
     * </p>
     */
    @ToString.Exclude
    String url;

    /**
     * Storage key for {@code GET /v3/domains/{domain_name}/messages/{storage_key}} (see {@link com.mailgun.api.v3.MailgunMessagesApi#getStoredMessage(String, String)}).
     */
    @ToString.Exclude
    String key;

}
