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

}
