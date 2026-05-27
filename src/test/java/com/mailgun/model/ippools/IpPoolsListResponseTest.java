package com.mailgun.model.ippools;

import com.mailgun.util.ObjectMapperUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class IpPoolsListResponseTest {

    @Test
    void deserializeListResponseTest() throws Exception {
        String json = "{\"ip_pools\":[{\"description\":\"desc\",\"ips\":[\"1.0.0.1\"],"
                + "\"is_inherited\":true,\"is_linked\":false,\"name\":\"n\",\"pool_id\":\"id1\"}],"
                + "\"message\":\"success\"}";

        IpPoolsListResponse result = ObjectMapperUtil.getObjectMapper().readValue(json, IpPoolsListResponse.class);

        assertNotNull(result);
        assertEquals("success", result.getMessage());
        assertEquals(1, result.getIpPools().size());
        assertEquals("id1", result.getIpPools().get(0).getPoolId());
        assertFalse(result.getIpPools().get(0).getIsLinked());
    }

}
