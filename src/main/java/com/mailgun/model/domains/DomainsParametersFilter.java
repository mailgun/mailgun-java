package com.mailgun.model.domains;

import com.mailgun.enums.DomainState;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;


/**
 * <p>
 * Filter for requesting a list of domains (GET /v4/domains). List is paginated and limited to 1000 items per page.
 * </p>
 *
 * @see <a href="https://documentation.mailgun.com/docs/mailgun/api-reference/send/mailgun/domains/get-v4-domains">Get domains</a>
 */
@Value
@Jacksonized
@Builder
public class DomainsParametersFilter {

    /**
     * <p>
     * Filter the list by a given DKIM authority name. If state is specified then only state filtering is applied.
     * </p>
     */
    String authority;

    /**
     * <p>
     * Filter the list by a given state. Can be <code>active</code>, <code>unverified</code> or <code>disabled</code>.
     * </p>
     * {@link DomainState}
     */
    String state;

    /**
     * <p>
     * Maximum number of records to return. Max: 1000. Default: 100.
     * </p>
     */
    Integer limit;

    /**
     * <p>
     * Number of records to skip. Default: 0.
     * </p>
     */
    Integer skip;

    /**
     * <p>
     * Sort option (e.g. created_at). Defaults to asc order. If not specified, domains are returned in reverse creation date order.
     * </p>
     */
    String sort;

    /**
     * <p>
     * Search domains by partial or complete name. Does not support wildcards.
     * </p>
     */
    String search;

    /**
     * <p>
     * Include domains that belong to any subaccounts under this account. Default: false.
     * </p>
     * <p>
     * Query param name: {@code include_subaccounts}.
     * </p>
     */
    Boolean include_subaccounts;

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

        /**
         * Include domains that belong to any subaccounts under this account.
         *
         * @param includeSubaccounts true to include subaccount domains
         * @return this builder
         */
        public DomainsParametersFilter.DomainsParametersFilterBuilder includeSubaccounts(Boolean includeSubaccounts) {
            this.include_subaccounts = includeSubaccounts;
            return this;
        }
    }

}
