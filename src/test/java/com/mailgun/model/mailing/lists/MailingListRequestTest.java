package com.mailgun.model.mailing.lists;

import com.mailgun.enums.AccessLevel;
import com.mailgun.enums.ReplyPreference;
import org.junit.jupiter.api.Test;

import static com.mailgun.constants.TestConstants.TEST_EMAIL_1;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MailingListRequestTest {

    @Test
    void builderSuccessTest() {
        String description = "Some description of test team";
        String mailingListName = "Test team 1";
        MailingListRequest request = MailingListRequest.builder()
                .address(TEST_EMAIL_1)
                .name(mailingListName)
                .description(description)
                .accessLevel(AccessLevel.EVERYONE)
                .replyPreference(ReplyPreference.LIST)
                .build();

        assertEquals(TEST_EMAIL_1, request.getAddress());
        assertEquals(mailingListName, request.getName());
        assertEquals(description, request.getDescription());
        assertEquals(AccessLevel.EVERYONE.getValue(), request.getAccessLevel());
        assertEquals(ReplyPreference.LIST.getValue(), request.getReplyPreference());
    }

}
