package com.mailgun.model.domains;

import com.mailgun.enums.DomainState;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;


/**
 * <p>
 * Filter for requesting a list of domains.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/en/latest/api-domains.html#domains">Domains</a>
 */
@Value
@Jacksonized
@Builder
public class DomainsParametersFilter {

    /**
     * <p>
     * Filter the list by a given DKIM authority name
     * </p>
     */
    String authority;

    /**
     * <p>
     * Filter the list by a given state.
     * </p>
     *
     * <p>
     * Can be <code>active</code>, <code>unverified</code> or <code>disabled</code>
     * </p>
     * {@link DomainState}
     */
    String state;

    /**
     * <p>
     * Maximum number of records to return. (100 by default)
     * </p>
     */
    Integer limit;

    /**
     * <p>
     * Number of records to skip. (0 by default)
     * </p>
     */
    Integer skip;

    public static class DomainsParametersFilterBuilder {

        /**
         * <p>
         * Filter the list by a given state.
         * </p>
         *
         * <p>
         * Can be <code>active</code>, <code>unverified</code> or <code>disabled</code>
         * </p>
         *
         * @param state {@link DomainState}
         * @return Returns a reference to this object so that method calls can be chained together.
         */
        public DomainsParametersFilter.DomainsParametersFilterBuilder state(DomainState state) {
            this.state = state.getValue();
            return this;
        }
    }

}
