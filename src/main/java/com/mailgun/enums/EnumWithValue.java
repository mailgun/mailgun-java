package com.mailgun.enums;

import java.util.Arrays;
import java.util.Optional;

public interface EnumWithValue {

    String getValue();

    /**
     * Resolve a documented enum value without failing for values added by the server in the future.
     *
     * @param enumType enum class
     * @param value raw API value
     * @param <T> enum type
     * @return matching enum constant, or empty when the value is unknown
     */
    static <T extends Enum<T> & EnumWithValue> Optional<T> fromValue(Class<T> enumType, String value) {
        if (value == null) {
            return Optional.empty();
        }
        return Arrays.stream(enumType.getEnumConstants())
            .filter(enumValue -> enumValue.getValue().equals(value))
            .findFirst();
    }
}
