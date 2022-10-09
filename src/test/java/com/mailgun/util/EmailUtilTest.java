package com.mailgun.util;

import org.junit.jupiter.api.Test;

import static com.mailgun.constants.TestConstants.TEST_EMAIL_1;
import static com.mailgun.constants.TestConstants.TEST_USER_NAME;
import static org.junit.jupiter.api.Assertions.*;

class EmailUtilTest {

    @Test
    void nameWithEmailTest() {
        String result = EmailUtil.nameWithEmail(TEST_USER_NAME, TEST_EMAIL_1);

        assertEquals("Zarathustra <some-fake-address-01@example.com>", result);
    }
}
