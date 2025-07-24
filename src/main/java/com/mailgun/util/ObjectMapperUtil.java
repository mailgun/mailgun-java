package com.mailgun.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import feign.Response;
import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;

@UtilityClass
public class ObjectMapperUtil {

    /**
     * <p>
     * Retrieves the response object decoded from the {@link feign.Response} body.
     * </p>
     *
     * @param response {@link feign.Response}
     * @param valueType expected response object type
     * @param <T> the specific kind of response object
     * @return return the response object decoded from the {@link feign.Response} body
     * @throws IOException  – If an I/O error occurs
     */
    public <T> T decode(Response response, Class<T> valueType) throws IOException {
        if (response.body() == null)
            return null;
        Reader reader = response.body().asReader(response.charset());
        if (!reader.markSupported()) {
            reader = new BufferedReader(reader, 1);
        }
        try {
            // Read the first byte to see if we have any data
            reader.mark(1);
            if (reader.read() == -1) {
                return null; // Eagerly returning null avoids "No content to map due to end-of-input"
            }
            reader.reset();

            ObjectMapper objectMapper = getObjectMapper();
            return objectMapper.readValue(reader, objectMapper.constructType(valueType));
        } catch (RuntimeJsonMappingException e) {
            if (e.getCause() instanceof IOException) {
                throw (IOException) e.getCause();
            }
            throw e;
        }
    }

    final List<Module> JACKSON_MODULES = Arrays.asList(
            new ParameterNamesModule(),
            new Jdk8Module(),
            new JavaTimeModule()
    );

    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModules(JACKSON_MODULES);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }

}
