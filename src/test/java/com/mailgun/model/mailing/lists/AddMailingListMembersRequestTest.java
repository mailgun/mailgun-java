package com.mailgun.model.mailing.lists;

import com.mailgun.enums.YesNo;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddMailingListMembersRequestTest {

    @Test
    void builderSuccessTest() {
        AddMailingListMembersRequest request = AddMailingListMembersRequest.builder()
                .members(Arrays.asList(MailingListMember.builder().build(), MailingListMember.builder().build()))
                .upsert(true)
                .build();

        assertEquals(YesNo.YES.getValue(), request.getUpsert());
        assertEquals(2, request.getMembers().size());
    }

}
