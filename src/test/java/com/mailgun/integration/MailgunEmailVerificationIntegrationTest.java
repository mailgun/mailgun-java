package com.mailgun.integration;

import com.mailgun.api.v4.MailgunEmailVerificationApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.verification.AddressValidationResponse;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.mailgun.constants.IntegrationTestConstants.EMAIL_TO;
import static com.mailgun.constants.IntegrationTestConstants.PRIVATE_API_KEY;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled("Use these tests as examples for a real implementation.")
class MailgunEmailVerificationIntegrationTest {

    private static MailgunEmailVerificationApi mailgunEmailVerificationApi;

    @BeforeAll
    static void beforeAll() {
        mailgunEmailVerificationApi = MailgunClient.config(PRIVATE_API_KEY)
                .createApi(MailgunEmailVerificationApi.class);
    }

    @Test
    void validateAddressSuccessTest() {
        AddressValidationResponse result = mailgunEmailVerificationApi.validateAddress(EMAIL_TO);

        assertTrue(StringUtils.isNotBlank(result.getAddress()));
        assertNotNull(result.getIsDisposableAddress());
        assertNotNull(result.getIsDisposableAddress());
        assertNotNull(result.getReason());
        assertTrue(StringUtils.isNotBlank(result.getResult()));
        assertTrue(StringUtils.isNotBlank(result.getRisk()));
    }

    @Test
    void validateAddressProviderLookupSuccessTest() {
        AddressValidationResponse result = mailgunEmailVerificationApi.validateAddress(EMAIL_TO, true);

        assertTrue(StringUtils.isNotBlank(result.getAddress()));
        assertNotNull(result.getIsDisposableAddress());
        assertNotNull(result.getIsDisposableAddress());
        assertNotNull(result.getReason());
        assertTrue(StringUtils.isNotBlank(result.getResult()));
        assertTrue(StringUtils.isNotBlank(result.getRisk()));
    }

    @Test
    void validateAddressPostSuccessTest() {
        AddressValidationResponse result = mailgunEmailVerificationApi.validateAddressPostRequest(EMAIL_TO);

        assertTrue(StringUtils.isNotBlank(result.getAddress()));
        assertNotNull(result.getIsDisposableAddress());
        assertNotNull(result.getIsDisposableAddress());
        assertNotNull(result.getReason());
        assertTrue(StringUtils.isNotBlank(result.getResult()));
        assertTrue(StringUtils.isNotBlank(result.getRisk()));
    }

    @Test
    void validateAddressPostProviderLookupSuccessTest() {
        AddressValidationResponse result = mailgunEmailVerificationApi.validateAddressPostRequest(EMAIL_TO, true);

        assertTrue(StringUtils.isNotBlank(result.getAddress()));
        assertNotNull(result.getIsDisposableAddress());
        assertNotNull(result.getIsDisposableAddress());
        assertNotNull(result.getReason());
        assertTrue(StringUtils.isNotBlank(result.getResult()));
        assertTrue(StringUtils.isNotBlank(result.getRisk()));
    }

}
