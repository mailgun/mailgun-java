package com.mailgun.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.codec.binary.Base64;

import static com.mailgun.constants.TestConstants.TEST_API_KEY;

@UtilityClass
public class TestHeadersUtils {

    public String getExpectedAuthHeader() {
        String usernamePassword = "api:" + TEST_API_KEY;
        return "Basic " + Base64.encodeBase64String(usernamePassword.getBytes());
    }

}
