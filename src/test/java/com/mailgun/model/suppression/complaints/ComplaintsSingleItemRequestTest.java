package com.mailgun.model.suppression.complaints;

import org.junit.jupiter.api.Test;

import static com.mailgun.constants.TestConstants.TEST_EMAIL_1;
import static com.mailgun.constants.TestConstants.ZONED_DATE_TIME_2018_2_03_GMT_STRING;
import static com.mailgun.constants.TestConstants.ZONED_DATE_TIME_2018_2_3_GMT;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ComplaintsSingleItemRequestTest {

    @Test
    void builderSuccessTest() {
        ComplaintsSingleItemRequest request = ComplaintsSingleItemRequest.builder()
                .address(TEST_EMAIL_1)
                .createdAt(ZONED_DATE_TIME_2018_2_3_GMT)
                .build();

        assertEquals(TEST_EMAIL_1, request.getAddress());
        assertEquals(ZONED_DATE_TIME_2018_2_03_GMT_STRING, request.getCreatedAt());
    }

}
