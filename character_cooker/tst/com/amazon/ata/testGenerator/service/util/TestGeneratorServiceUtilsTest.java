package com.amazon.ata.testGenerator.service.util;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static com.amazon.ata.testGenerator.service.util.TestGeneratorServiceUtils.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGeneratorServiceUtilsTest {

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void isValidUsername_returnsFalse() {
        // Given
        String strQuote = "hello\"";
        String strBackSlash = "hello\\";
        String strBlanks = "   ";
        String strLength = "cat";

        // When
        boolean quote = isValidUsername(strQuote);
        boolean backslash = isValidUsername(strBackSlash);
        boolean blank = isValidUsername(strBlanks);
        boolean length = isValidUsername(strLength);

        // Then
        assertFalse(quote);
        assertFalse(backslash);
        assertFalse(blank);
        assertFalse(length);
    }

    @Test
    public void isValidPassword_returnsFalse() {
        // Given
        String strQuote = "hello\"";
        String strBackSlash = "hello\\";
        String strBlanks = "   ";
        String strLength = "cat";

        // When
        boolean quote = isValidPassword(strQuote);
        boolean backslash = isValidPassword(strBackSlash);
        boolean blank = isValidPassword(strBlanks);
        boolean length = isValidPassword(strLength);

        // Then
        assertFalse(quote);
        assertFalse(backslash);
        assertFalse(blank);
        assertFalse(length);
    }

    @Test
    public void isValidTitle_returnsFalse() {
        // Given
        String strQuote = "hello\"";
        String strBackSlash = "hello\\";
        String strBlanks = "   ";
        String strEmpty = "";

        // When
        boolean quote = isValidTitle(strQuote);
        boolean backslash = isValidTitle(strBackSlash);
        boolean blank = isValidTitle(strBlanks);
        boolean empty = isValidTitle(strEmpty);

        // Then
        assertFalse(quote);
        assertFalse(backslash);
        assertFalse(blank);
        assertFalse(empty);
    }

    @Test
    public void GenerateId_returnsId() {
        // When
        String templateId = generateTemplateId();
        String termId = generateCustomTermId();

        // Then
        assertTrue(StringUtils.isAlphanumeric(templateId));
        assertTrue(StringUtils.isAlphanumeric(termId));
    }


}
