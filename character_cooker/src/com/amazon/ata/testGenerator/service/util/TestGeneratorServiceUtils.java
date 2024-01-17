package com.amazon.ata.testGenerator.service.util;

import org.junit.platform.commons.util.StringUtils;

import java.util.regex.Pattern;

public class TestGeneratorServiceUtils {
    private static final Pattern INVALID_CHARACTER_PATTERN = Pattern.compile("[\"\'\\\\ ]");

    static final int PLAYLIST_ID_LENGTH = 5;

    private TestGeneratorServiceUtils() {}

    public static boolean isValidString(final String stringToValidate) {
        if (StringUtils.isBlank(stringToValidate)) {
            return false;
        }

        return !INVALID_CHARACTER_PATTERN.matcher(stringToValidate).find();
    }

    public static String generatePlaylistId() {
        return "";
    }

}
