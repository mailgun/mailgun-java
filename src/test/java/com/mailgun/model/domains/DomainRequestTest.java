package com.mailgun.model.domains;

import com.mailgun.enums.SpamAction;
import com.mailgun.enums.WebScheme;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.mailgun.constants.TestConstants.TEST_DOMAIN;
import static com.mailgun.constants.TestConstants.TEST_IP_1;
import static com.mailgun.constants.TestConstants.TEST_IP_2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DomainRequestTest {

    @Test
    void builderSuccessTest() {
        DomainRequest request = DomainRequest.builder()
                .name(TEST_DOMAIN)
                .spamAction(SpamAction.BLOCK)
                .wildcard(true)
                .forceDkimAuthority(false)
                .dkimKeySize(1024)
                .ips(Arrays.asList(TEST_IP_1, TEST_IP_2))
                .webScheme(WebScheme.HTTPS)
                .build();

        assertEquals(TEST_DOMAIN, request.getName());
        assertEquals(SpamAction.BLOCK.getValue(), request.getSpamAction());
        assertEquals(true, request.getWildcard());
        assertEquals(false, request.getForceDkimAuthority());
        assertEquals(1024, request.getDkimKeySize());
        assertTrue(request.getIps().containsAll(Arrays.asList(TEST_IP_1, TEST_IP_2)));
        assertEquals(WebScheme.HTTPS.getValue(), request.getWebScheme());
    }

}
