package com.amazon.ata.testGenerator.service.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

/**
 * This class contains the helper functions to validate usernames, passwords and titles from users.
 * This class also provides template IDs and termIDs
 *
 *
 */
public class TestGeneratorServiceUtils {
    private static final Pattern INVALID_ACCOUNT_PATTERN = Pattern.compile("[\"\'\\\\ ]");
    private static final Pattern INVALID_TITLE_PATTERN = Pattern.compile("[\"\'\\\\]");

    static final int USERNAME_LENGTH = 5;
    static final int PASSWORD_LENGTH = 5;

    static final int TEMPLATE_ID_LENGTH = 6;
    static final int CUSTOM_TERM_ID_LENGTH = 5;
    static final int TERM_ID_LENGTH = 4;

    private TestGeneratorServiceUtils() {}

    // Validate Inputs
    // NOTE: extra validations are checked in the needed service class

    /**
     * Checks that the provided String contains only valid characters.
     *
     * @param username the username to be validated
     * @return true if the String is valid (contains only valid characters),
     *         false otherwise
     */
    public static boolean isValidUsername(final String username) {
        if (StringUtils.isBlank(username)) {
            return false;
        }
        if (username.length() < USERNAME_LENGTH) {
            return false;
        }
        return !INVALID_ACCOUNT_PATTERN.matcher(username).find();
    }

    public static boolean isValidPassword(final String password) {
        if (StringUtils.isBlank(password)) {
            return false;
        }
        if (password.length() < PASSWORD_LENGTH) {
            return false;
        }
        return !INVALID_ACCOUNT_PATTERN.matcher(password).find();
    }

    public static boolean isValidTitle(final String title) {
        if (StringUtils.isBlank(title)) {
            return false;
        }
        if (title.isEmpty()) {
            return false;
        }
        return !INVALID_TITLE_PATTERN.matcher(title).find();
    }

    // Generate IDS

    public static String generateTemplateId() {
        return RandomStringUtils.randomAlphanumeric(TEMPLATE_ID_LENGTH);
    }

    public static String generateCustomTermId() {
        return RandomStringUtils.randomAlphanumeric(CUSTOM_TERM_ID_LENGTH);
    }

    public static String getDate() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        return currentDateTime.format(formatter);
    }



}
