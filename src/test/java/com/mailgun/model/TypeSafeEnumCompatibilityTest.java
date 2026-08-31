package com.mailgun.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mailgun.enums.ApiKeyKind;
import com.mailgun.enums.ApiKeyRole;
import com.mailgun.model.keys.ApiKey;
import com.mailgun.model.keys.ApiKeyCreateRequest;
import com.mailgun.util.ObjectMapperUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TypeSafeEnumCompatibilityTest {

    private static final ObjectMapper OBJECT_MAPPER = ObjectMapperUtil.getObjectMapper();

    @Test
    void rawStringBuilderMethodsRemainAvailable() {
        ApiKeyCreateRequest request = ApiKeyCreateRequest.builder()
            .kind("future-kind")
            .role("future-role")
            .build();

        assertEquals("future-kind", request.getKind());
        assertEquals("future-role", request.getRole());
    }

    @Test
    void typedBuilderMethodsStoreDocumentedValues() {
        ApiKeyCreateRequest request = ApiKeyCreateRequest.builder()
            .kindEnum(ApiKeyKind.WEB)
            .roleEnum(ApiKeyRole.DEVELOPER)
            .build();

        assertEquals("web", request.getKind());
        assertEquals("developer", request.getRole());
    }

    @Test
    void unknownResponseValuesRemainAccessibleAsStrings() {
        ApiKey apiKey = ApiKey.builder()
            .kind("future-kind")
            .role("future-role")
            .build();

        assertEquals("future-kind", apiKey.getKind());
        assertEquals("future-role", apiKey.getRole());
        assertFalse(apiKey.getKindEnum().isPresent());
        assertFalse(apiKey.getRoleEnum().isPresent());
    }

    @Test
    void lombokEnumGetterPreservesJsonValueSerialization() throws Exception {
        assertEquals("\"domain\"", OBJECT_MAPPER.writeValueAsString(ApiKeyKind.DOMAIN));
        assertEquals("\"sending\"", OBJECT_MAPPER.writeValueAsString(ApiKeyRole.SENDING));
    }
}
