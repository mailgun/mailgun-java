package com.mailgun.model.mailing.lists;

import com.mailgun.enums.YesNo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MailingListMembersRequestTest {

    @Test
    void builderSuccessTest() {
        MailingListMembersRequest request = MailingListMembersRequest.builder()
                .limit(1)
                .subscribed(true)
                .build();

        assertEquals(1, request.getLimit());
        assertEquals(YesNo.YES.getValue(), request.getSubscribed());
    }

}
