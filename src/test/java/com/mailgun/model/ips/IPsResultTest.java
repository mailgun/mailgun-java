package com.mailgun.model.ips;

import com.mailgun.util.ObjectMapperUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class IPsResultTest {

    @Test
    void deserializeIpsResultWithDetailsTest() throws Exception {
        String json = "{\"assignable_to_pools\":[\"5.6.7.8\"],\"details\":[{\"ip\":\"1.2.3.4\","
                + "\"is_on_warmup\":true,\"dedicated\":true,\"enabled\":true}],"
                + "\"items\":[\"1.2.3.4\"],\"total_count\":1}";

        IPsResult result = ObjectMapperUtil.getObjectMapper().readValue(json, IPsResult.class);

        assertNotNull(result);
        assertEquals(1, result.getTotalCount());
        assertEquals(1, result.getAssignableToPools().size());
        assertEquals(1, result.getDetails().size());
        assertEquals(Boolean.TRUE, result.getDetails().get(0).getIsOnWarmup());
    }

}
