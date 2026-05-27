package com.mailgun.model.templates;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mailgun.util.ObjectMapperUtil;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CopyTemplateRequestTest {

    @Test
    void serializeCopyTemplateRequestTest() throws JsonProcessingException {
        CopyTemplateRequest request = CopyTemplateRequest.builder()
                .request(CopyTemplateItem.builder()
                        .accountId("acct-1")
                        .name("welcome-copy")
                        .domain("example.com")
                        .build())
                .sourceVersions(Collections.singletonList("v1"))
                .build();

        String json = ObjectMapperUtil.getObjectMapper().writeValueAsString(request);

        assertEquals(
                "{\"requests\":[{\"name\":\"welcome-copy\",\"domain\":\"example.com\",\"account_id\":\"acct-1\"}],"
                        + "\"source_versions\":[\"v1\"]}",
                json);
    }

    @Test
    void deserializeCopyTemplateResponseEmptyFailedCopiesTest() throws JsonProcessingException {
        CopyTemplateResponse response = ObjectMapperUtil.getObjectMapper()
                .readValue("{\"message\":\"ok\",\"failed_copies\":[]}", CopyTemplateResponse.class);

        assertEquals("ok", response.getMessage());
        assertTrue(response.getFailedCopies().isEmpty());
    }
}
