package com.mailgun.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mailgun.exception.MailGunException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StringUtilTest {

    @Test
    void asJsonString_validObject_returnsJsonString() {
        // Arrange
        Object testObject = new Object() {
            public String name = "Test";
            public int value = 42;
        };

        // Act
        String jsonString = StringUtil.asJsonString(testObject);

        // Assert
        assertNotNull(jsonString);
        assertTrue(jsonString.contains("\"name\":\"Test\""));
        assertTrue(jsonString.contains("\"value\":42"));
    }

    @Test
    void asJsonString_invalidObject_throwsMailGunException() throws JsonProcessingException {
        // Arrange
        Object invalidObject = mock(Object.class);
        ObjectMapper objectMapperMock = mock(ObjectMapper.class);

        try (var mockedStatic = mockStatic(ObjectMapperUtil.class)) {
            mockedStatic.when(ObjectMapperUtil::getObjectMapper).thenReturn(objectMapperMock);
            try {
                when(objectMapperMock.writeValueAsString(invalidObject)).thenThrow(new RuntimeException("Serialization error"));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            // Act & Assert
            MailGunException exception = assertThrows(MailGunException.class, () -> StringUtil.asJsonString(invalidObject));
            assertEquals("Unable to serialize object.", exception.getMessage());
        }
    }
}