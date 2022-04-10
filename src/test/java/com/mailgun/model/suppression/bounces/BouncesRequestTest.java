package com.mailgun.model.suppression.bounces;

import org.junit.jupiter.api.Test;

import static com.mailgun.constants.TestConstants.TEST_EMAIL_1;
import static com.mailgun.constants.TestConstants.ZONED_DATE_TIME_2018_2_3_GMT;
import static com.mailgun.constants.TestConstants.ZONED_DATE_TIME_2018_2_3_GMT_STRING;
import static javax.servlet.RequestDispatcher.ERROR_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BouncesRequestTest {

    @Test
    void builderSuccessTest() {
        BouncesRequest request = BouncesRequest.builder()
                .address(TEST_EMAIL_1)
                .code("550")
                .error(ERROR_MESSAGE)
                .createdAt(ZONED_DATE_TIME_2018_2_3_GMT)
                .build();

        assertEquals(TEST_EMAIL_1, request.getAddress());
        assertEquals("550", request.getCode());
        assertEquals(ERROR_MESSAGE, request.getError());
        assertEquals(ZONED_DATE_TIME_2018_2_3_GMT_STRING, request.getCreatedAt());
    }

}
