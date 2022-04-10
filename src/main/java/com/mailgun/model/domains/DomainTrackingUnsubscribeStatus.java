package com.mailgun.model.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * <p>
 * Domain unsubscribe tracking settings statuses.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-domains.html#domains">Domains</a>
 */
@Value
@Jacksonized
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DomainTrackingUnsubscribeStatus {

    /**
     * <p>
     * Domain tracking settings status.
     * </p>
     *
     * <p>
     * <code>true</code> or <code>false</code>
     * </p>
     */
    Boolean active;

    /**
     * <p>
     * Custom HTML version of unsubscribe footer.
     * </p>
     */
    @JsonProperty("html_footer")
    String htmlFooter;

    /**
     * <p>
     * Custom text version of unsubscribe footer.
     * </p>
     *
     * <p>
     * Mailgun can automatically provide an unsubscribe footer in each email you send and also provides you with several unsubscribe variables.
     * You can customize your unsubscribe footer by editing the settings in the Control Panel.
     * </p>
     *
     * @see <a href="https://documentation.mailgun.com/en/latest/user_manual.html#um-tracking-unsubscribes">Tracking Unsubscribes</a>
     */
    @JsonProperty("text_footer")
    String textFooter;

}
