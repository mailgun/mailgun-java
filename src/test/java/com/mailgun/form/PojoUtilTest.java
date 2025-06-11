package com.mailgun.form;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Map;

import feign.form.FormProperty;

import static com.mailgun.form.PojoUtil.isUserPojo;
import static com.mailgun.form.PojoUtil.toMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PojoUtilTest {

    // A small bean for toMap(...)
    static class TestBean {
        private String name = "Alice";
        @FormProperty("ageProp")
        private int age = 42;
        @CustomProperties(prefix = "cp_")
        private Map<String, String> extra = Map.of("k1","v1","k2","v2");
        private final String finalField = "ignored";
        private static String staticField = "ignored";
        private String nullField = null;
    }

    @Test
    void isUserPojo_objectOverload() {
        assertFalse(isUserPojo("foo"));
        assertTrue(isUserPojo(new TestBean()));
    }

    @Test
    void isUserPojo_typeOverload() {
        assertFalse(isUserPojo(String.class));
        assertTrue(isUserPojo(TestBean.class));
    }

    @Test
    void toMap_transformsBeanCorrectly() {
        TestBean bean = new TestBean();
        var map = toMap(bean);

        assertEquals("Alice",    map.get("name"));
        assertEquals(42,         map.get("ageProp"));
        assertEquals("v1",       map.get("cp_k1"));
        assertEquals("v2",       map.get("cp_k2"));
        assertFalse(map.containsKey("finalField"));
        assertFalse(map.containsKey("staticField"));
        assertFalse(map.containsKey("nullField"));
        assertEquals(4, map.size());  // name, ageProp, cp_k1, cp_k2
    }

    @Test
    void setAccessibleAction_viaReflection() throws Exception {
        // 1) Prepare a private field on a local class
        class LocalBean { private String secret = "shh"; }
        LocalBean bean = new LocalBean();

        Field secretField = LocalBean.class.getDeclaredField("secret");
        // attempt to hide the field (may not be enforced by canAccess)
        secretField.setAccessible(false);

        // 2) Load the private inner class
        Class<?> actionClass = Class.forName("com.mailgun.form.PojoUtil$SetAccessibleAction");

        // 3) Instantiate it via the no-arg constructor
        Constructor<?> ctor = actionClass.getDeclaredConstructor();
        ctor.setAccessible(true);
        Object actionInstance = ctor.newInstance();

        // 4) Call its generated setter setField(Field)
        Method setter = actionClass.getDeclaredMethod("setField", Field.class);
        setter.setAccessible(true);
        setter.invoke(actionInstance, secretField);

        // 5) Run it under AccessController
        @SuppressWarnings("unchecked")
        PrivilegedAction<?> pa = (PrivilegedAction<?>) actionInstance;
        AccessController.doPrivileged(pa);

        // 6) Now the field *must* be accessible and yield the right value
        assertTrue(secretField.canAccess(bean), "Field should be accessible after SetAccessibleAction");
        assertEquals("shh", secretField.get(bean));
    }
}