package com.mailgun.model.domains;

import com.mailgun.enums.DomainState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DomainsParametersFilterTest {

    @Test
    void builderSuccessTest() {
        DomainsParametersFilter filter = DomainsParametersFilter.builder()
                .state(DomainState.ACTIVE)
                .limit(2)
                .skip(3)
                .build();

        assertEquals(DomainState.ACTIVE.getValue(), filter.getState());
        assertEquals(2, filter.getLimit());
        assertEquals(3, filter.getSkip());
    }

    @Test
    void builderWithSortSearchIncludeSubaccountsTest() {
        DomainsParametersFilter filter = DomainsParametersFilter.builder()
                .state(DomainState.UNVERIFIED)
                .authority("example.com")
                .limit(1000)
                .skip(0)
                .sort("created_at")
                .search("my-domain")
                .includeSubaccounts(true)
                .build();

        assertEquals(DomainState.UNVERIFIED.getValue(), filter.getState());
        assertEquals("example.com", filter.getAuthority());
        assertEquals(1000, filter.getLimit());
        assertEquals("created_at", filter.getSort());
        assertEquals("my-domain", filter.getSearch());
        assertEquals(Boolean.TRUE, filter.getInclude_subaccounts());
    }

}
