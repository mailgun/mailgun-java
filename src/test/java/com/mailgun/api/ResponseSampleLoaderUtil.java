package com.mailgun.api;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ResponseSampleLoaderUtil {

    private static final String JSON_BASE_PATH = "src/test/resources/response";

    @SneakyThrows
    public String fromFile(String path) {
        byte[] bytes = Files.readAllBytes(Paths.get(JSON_BASE_PATH + "/" + path));
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
