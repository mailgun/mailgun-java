package com.mailgun.model.domains;

import feign.form.FormProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@Builder
public class DomainUnsubscribeConnectionSettingsRequest {

    /**
     * <p>
     * Active status
     * <code>true</code> or <code>false</code>
     * </p>
     */
    Boolean active;

    /**
     * <p>
     * Custom HTML version of unsubscribe footer.
     * </p>
     */
    @FormProperty("html_footer")
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
    @FormProperty("text_footer")
    String textFooter;

}
