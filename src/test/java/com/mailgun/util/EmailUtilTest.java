package com.mailgun.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailUtilTest {

    @Test
    void nameWithEmail_withNameAndEmail_returnsFormattedString() {
        // Arrange
        String name = "John Doe";
        String email = "john.doe@example.com";

        // Act
        String result = EmailUtil.nameWithEmail(name, email);

        // Assert
        assertEquals("John Doe <john.doe@example.com>", result);
    }

    @Test
    void nameWithEmail_withBlankName_returnsEmailOnly() {
        // Arrange
        String name = " ";
        String email = "john.doe@example.com";

        // Act
        String result = EmailUtil.nameWithEmail(name, email);

        // Assert
        assertEquals("john.doe@example.com", result);
    }

    @Test
    void nameWithEmail_withNullName_returnsEmailOnly() {
        // Arrange
        String name = null;
        String email = "john.doe@example.com";

        // Act
        String result = EmailUtil.nameWithEmail(name, email);

        // Assert
        assertEquals("john.doe@example.com", result);
    }

    @Test
    void nameWithEmail_withEmptyName_returnsEmailOnly() {
        // Arrange
        String name = "";
        String email = "john.doe@example.com";

        // Act
        String result = EmailUtil.nameWithEmail(name, email);

        // Assert
        assertEquals("john.doe@example.com", result);
    }

    @Test
    void nameWithEmail_whenNameBlank_returnsEmailOnly() {
        assertEquals("foo@bar.com", EmailUtil.nameWithEmail("", "foo@bar.com"));
    }
    @Test
    void nameWithEmail_whenNamePresent_returnsNameWithBrackets() {
        assertEquals("Alice <alice@example.com>",
                EmailUtil.nameWithEmail("Alice", "alice@example.com"));
    }
}