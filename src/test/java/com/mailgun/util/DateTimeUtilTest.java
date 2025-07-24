package com.mailgun.util;

import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeUtilTest {

    // A fixed date‐time for reproducible tests: 2025-05-28T12:34:56
    private final ZonedDateTime baseUtc = ZonedDateTime.of(
            2025, 5, 28, 12, 34, 56, 0,
            ZoneId.of("UTC")
    );

    @Test
    void toString_rfc1123ProducesGmtSuffix() {
        String s = DateTimeUtil.toString(baseUtc);
        // RFC_1123 uses "GMT" as the zone-name
        assertEquals("Wed, 28 May 2025 12:34:56 GMT", s);
    }

    @Test
    void toString_nullInputReturnsNull() {
        assertNull(DateTimeUtil.toString(null));
    }

    @Test
    void toStringNumericTimeZone_rendersNumericOffset() {
        // For UTC the numeric offset is +0000
        String s = DateTimeUtil.toStringNumericTimeZone(baseUtc);
        assertTrue(s.startsWith("Wed, 28 May 2025 12:34:56 "), "pattern prefix");
        assertTrue(s.endsWith("+0000"), "numeric timezone offset");
    }

    @Test
    void toStringNumericTimeZone_nullInputReturnsNull() {
        assertNull(DateTimeUtil.toStringNumericTimeZone(null));
    }

    @Test
    void toStringNameTimeZone_rendersZoneName() {
        // Test with a named zone to ensure the zone‐name appears
        ZonedDateTime kievTime = baseUtc.withZoneSameInstant(ZoneId.of("Europe/Kiev"));
        String s = DateTimeUtil.toStringNameTimeZone(kievTime);
        // Should contain the zone‐name, e.g. "EEST" or "EET", depending on date
        assertTrue(s.contains("Europe/Kiev") || s.contains("EEST") || s.contains("EET"),
                "should include the zone name or abbreviation");
        // And it should start with the day‐of‐week/day‐month pattern
        assertTrue(s.startsWith("Wed, 28 May 2025"), "pattern prefix");
    }

    @Test
    void toStringNameTimeZone_nullInputReturnsNull() {
        assertNull(DateTimeUtil.toStringNameTimeZone(null));
    }
}