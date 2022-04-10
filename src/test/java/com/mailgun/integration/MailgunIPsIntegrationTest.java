package com.mailgun.integration;

import com.mailgun.api.v3.MailgunIPsApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.ResponseWithMessage;
import com.mailgun.model.ips.IPResult;
import com.mailgun.model.ips.IPsResult;
import com.mailgun.util.ObjectMapperUtil;
import feign.Request;
import feign.Response;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static com.mailgun.constants.IntegrationTestConstants.IP_1;
import static com.mailgun.constants.IntegrationTestConstants.IP_2;
import static com.mailgun.constants.IntegrationTestConstants.MAIN_DOMAIN;
import static com.mailgun.constants.IntegrationTestConstants.PRIVATE_API_KEY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled("Use these tests as examples for a real implementation.")
class MailgunIPsIntegrationTest {

    private static MailgunIPsApi mailgunIPsApi;

    @BeforeAll
    static void beforeAll() {
        mailgunIPsApi = MailgunClient.config(PRIVATE_API_KEY)
                .createApi(MailgunIPsApi.class);
    }

    @Test
    void getAllIPsSuccessTest() {
        IPsResult result = mailgunIPsApi.getAllIPs();

        List<String> items = result.getItems();
        assertTrue(CollectionUtils.isNotEmpty(items));
        assertTrue(StringUtils.isNotEmpty(items.get(0)));
        assertNotNull(result.getTotalCount());
    }

    @Test
    void getAllIPsFeignResponseSuccessTest() throws IOException {
        Response feignResponse = mailgunIPsApi.getAllIPsFeignResponse();

        assertEquals(200, feignResponse.status());
        assertEquals("OK", feignResponse.reason());
        Request request = feignResponse.request();
        assertEquals(Request.HttpMethod.GET, request.httpMethod());
        assertNotNull(feignResponse.body());
        IPsResult iPsResult = ObjectMapperUtil.decode(feignResponse, IPsResult.class);
        List<String> items = iPsResult.getItems();
        assertTrue(CollectionUtils.isNotEmpty(items));
        assertTrue(StringUtils.isNotEmpty(items.get(0)));
        assertNotNull(iPsResult.getTotalCount());
    }

    @Test
    void getDedicatedIPsSuccessTest() {
        IPsResult result = mailgunIPsApi.getDedicatedIPs(true);

        List<String> items = result.getItems();
        assertTrue(CollectionUtils.isNotEmpty(items));
        assertTrue(StringUtils.isNotEmpty(items.get(0)));
        assertNotNull(result.getTotalCount());
    }

    @Test
    void getSpecifiedIPSuccessTest() {
        IPResult result = mailgunIPsApi.getSpecifiedIP(IP_2);

        assertNotNull(result.getIp());
        assertNotNull(result.getDedicated());
        assertNotNull(result.getRdns());
    }

    @Test
    void getDomainIPsSuccessTest() {
        IPsResult result = mailgunIPsApi.getDomainIPs(MAIN_DOMAIN);

        List<String> items = result.getItems();
        assertTrue(CollectionUtils.isNotEmpty(items));
        assertTrue(StringUtils.isNotEmpty(items.get(0)));
        assertNotNull(result.getTotalCount());
    }

    @Test
    void assignIPToDomainSuccessTest() {
        ResponseWithMessage result = mailgunIPsApi.assignIPToDomain(MAIN_DOMAIN, IP_1);

        assertTrue(StringUtils.isNotEmpty(result.getMessage()));
    }

    @Test
    void unassignIPFromDomainSuccessTest() {
        ResponseWithMessage result = mailgunIPsApi.unassignIPFromDomain(MAIN_DOMAIN, IP_1);

        assertEquals("success", result.getMessage());
    }

}
