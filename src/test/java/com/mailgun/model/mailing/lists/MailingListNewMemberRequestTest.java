package com.mailgun.model.mailing.lists;

import com.mailgun.enums.YesNo;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.mailgun.constants.TestConstants.AGE;
import static com.mailgun.constants.TestConstants.AGE_VALUE;
import static com.mailgun.constants.TestConstants.GENDER;
import static com.mailgun.constants.TestConstants.MALE;
import static com.mailgun.constants.TestConstants.TEST_EMAIL_1;
import static com.mailgun.constants.TestConstants.TEST_USER_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MailingListNewMemberRequestTest {

    @Test
    void builderSuccessTest() {
        Map<String, Object> vars = new HashMap<>();
        vars.put(GENDER, MALE);
        vars.put(AGE, AGE_VALUE);

        MailingListNewMemberRequest request = MailingListNewMemberRequest.builder()
                .address(TEST_EMAIL_1)
                .name(TEST_USER_NAME)
                .vars(vars)
                .subscribed(true)
                .upsert(false)
                .build();

        assertEquals(TEST_EMAIL_1, request.getAddress());
        assertEquals(TEST_USER_NAME, request.getName());
        assertTrue(request.getVars().entrySet().containsAll(vars.entrySet()));
        assertEquals(YesNo.YES.getValue(), request.getSubscribed());
        assertEquals(YesNo.NO.getValue(), request.getUpsert());
    }

}
