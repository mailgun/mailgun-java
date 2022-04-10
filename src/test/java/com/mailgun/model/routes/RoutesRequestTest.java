package com.mailgun.model.routes;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.mailgun.constants.TestConstants.TEST_EMAIL_2;
import static com.mailgun.constants.TestConstants.TEST_EMAIL_3;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RoutesRequestTest {

    @Test
    void builderSuccessTest() {
        String description = "Java test route";
        String expression = "match_recipient('.*fake-address-01@example.com')";
        String action_1 = "forward('" + TEST_EMAIL_2 + "')";
        String action_2 = "forward('" + TEST_EMAIL_3 + "')";
        String action_3 = "forward('https://myhost.com/messages')";
        String action_4 = "stop()";

        RoutesRequest request = RoutesRequest.builder()
                .priority(2)
                .description(description)
                .expression(expression)
                .action(action_1)
                .action(action_2)
                .actions(Arrays.asList(action_3, action_4))
                .build();

        assertEquals(2, request.getPriority());
        assertEquals(description, request.getDescription());
        assertEquals(expression, request.getExpression());
        assertTrue(request.getAction().containsAll(Arrays.asList(action_1, action_2, action_3, action_4)));
    }

}
