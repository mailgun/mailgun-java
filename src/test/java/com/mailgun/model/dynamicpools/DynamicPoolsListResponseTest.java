package com.mailgun.model.dynamicpools;

import com.mailgun.util.ObjectMapperUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DynamicPoolsListResponseTest {

    @Test
    void deserializeDynamicPoolsListResponseTest() throws Exception {
        String json = "{\"dynamic_good\":[\"1.2.3.4\"],\"dynamic_poor\":[],\"message\":\"success\"}";

        DynamicPoolsListResponse result = ObjectMapperUtil.getObjectMapper()
                .readValue(json, DynamicPoolsListResponse.class);

        assertNotNull(result);
        assertEquals("success", result.getMessage());
        assertEquals(1, result.getDynamicGood().size());
    }

}
