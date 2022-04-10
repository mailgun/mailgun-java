package com.mailgun.model.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mailgun.enums.YesNoHtml;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Domain click settings status.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-domains.html#domains">Domains</a>
 */
@Value
@Jacksonized
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DomainClickTrackingStatus {

    /**
     * <p>
     * Domain click settings status.
     * </p>
     *
     * <p>
     * <code>TRUE</code>, <code>FALSE</code> or <code>HTML_ONLY</code>
     * </p>
     * {@link YesNoHtml}
     */
    YesNoHtml active;

}
