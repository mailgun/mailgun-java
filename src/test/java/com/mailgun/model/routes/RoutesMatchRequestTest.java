package com.mailgun.model.routes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoutesMatchRequestTest {

    @Test
    void builderSuccessTest() {
        String address = "test@example.com";

        RoutesMatchRequest request = RoutesMatchRequest.builder()
                .address(address)
                .build();

        assertEquals(address, request.getAddress());
    }

}
