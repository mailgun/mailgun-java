package com.mailgun.model.domains;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


/**
 * <p>
 * Domain credentials.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-domains.html#domains">Domains</a>
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class DomainCredentials {

    /**
     * <p>
     * Login
     * </p>
     */
    String login;

    /**
     * <p>
     * A password for the SMTP credentials. (Length Min 5, Max 32)
     * </p>
     */
    String password;

}

