package com.mailgun.form;

import feign.form.FormProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.rmi.UnexpectedException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashMap;
import java.util.Map;

import static java.lang.reflect.Modifier.isFinal;
import static java.lang.reflect.Modifier.isStatic;
import static lombok.AccessLevel.PRIVATE;

/**
 *
 * @author Artem Labazin
 */
public final class PojoUtil {

    public static boolean isUserPojo (@NonNull Object object) {
        val type = object.getClass();
        val packageName = type.getPackage().getName();
        return !packageName.startsWith("java.");
    }

    public static boolean isUserPojo (@NonNull Type type) {
        val typeName = type.toString();
        return !typeName.startsWith("class java.");
    }

    @SneakyThrows
    public static Map<String, Object> toMap (@NonNull Object object) {
        val result = new HashMap<String, Object>();
        val type = object.getClass();
        val setAccessibleAction = new PojoUtil.SetAccessibleAction();
        for (val field : type.getDeclaredFields()) {
            val modifiers = field.getModifiers();
            if (isFinal(modifiers) || isStatic(modifiers)) {
                continue;
            }
            setAccessibleAction.setField(field);
            AccessController.doPrivileged(setAccessibleAction);

            val fieldValue = field.get(object);
            if (fieldValue == null) {
                continue;
            }
            if (field.isAnnotationPresent(CustomProperties.class)) {
                String prefix = field.getAnnotation(CustomProperties.class).prefix();
                Map<String, String> properties = (Map<String, String>) fieldValue;
                for (Map.Entry<String, String> entry : properties.entrySet()) {
                    result.put(prefix + entry.getKey(), entry.getValue());
                }
            } else {
                val propertyKey = field.isAnnotationPresent(FormProperty.class)
                        ? field.getAnnotation(FormProperty.class).value()
                        : field.getName();

                result.put(propertyKey, fieldValue);
            }

        }
        return result;
    }

    private PojoUtil () throws UnexpectedException {
        throw new UnexpectedException("It is not allowed to instantiate this class");
    }

    @Setter
    @NoArgsConstructor
    @FieldDefaults(level = PRIVATE)
    private static class SetAccessibleAction implements PrivilegedAction<Object> {

        Field field;

        @Override
        public Object run () {
            field.setAccessible(true);
            return null;
        }
    }
}
