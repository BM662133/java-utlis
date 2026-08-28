package com.example.textutils;

import java.util.Locale;

/**
 * Stateless helper methods for common text operations.
 */
public final class TextUtils {

    private TextUtils() {
        throw new AssertionError("TextUtils cannot be instantiated");
    }

    /**
     * Checks whether text reads the same forwards and backwards, ignoring case
     * and non-alphanumeric characters.
     */
    public static boolean isPalindrome(String text) {
        requireText(text);

        int left = 0;
        int right = text.length();
        while (left < right) {
            int leftCodePoint = text.codePointAt(left);
            if (!Character.isLetterOrDigit(leftCodePoint)) {
                left += Character.charCount(leftCodePoint);
                continue;
            }

            int rightCodePoint = text.codePointBefore(right);
            if (!Character.isLetterOrDigit(rightCodePoint)) {
                right -= Character.charCount(rightCodePoint);
                continue;
            }

            if (Character.toLowerCase(leftCodePoint) != Character.toLowerCase(rightCodePoint)) {
                return false;
            }
            left += Character.charCount(leftCodePoint);
            right -= Character.charCount(rightCodePoint);
        }
        return true;
    }

    /** Counts groups of characters separated by whitespace. */
    public static int countWords(String text) {
        requireText(text);
        String normalized = text.strip();
        return normalized.isEmpty() ? 0 : normalized.split("\\s+").length;
    }

    /** Reverses text by Unicode code point rather than UTF-16 character. */
    public static String reverse(String text) {
        requireText(text);
        int[] codePoints = text.codePoints().toArray();
        StringBuilder result = new StringBuilder(text.length());
        for (int index = codePoints.length - 1; index >= 0; index--) {
            result.appendCodePoint(codePoints[index]);
        }
        return result.toString();
    }

    /** Capitalizes the first character of every whitespace-separated word. */
    public static String capitalizeWords(String text) {
        requireText(text);
        StringBuilder result = new StringBuilder(text.length());
        boolean capitalizeNext = true;

        for (int offset = 0; offset < text.length();) {
            int codePoint = text.codePointAt(offset);
            if (Character.isWhitespace(codePoint)) {
                result.appendCodePoint(codePoint);
                capitalizeNext = true;
            } else if (capitalizeNext) {
                String character = new String(Character.toChars(codePoint));
                result.append(character.toUpperCase(Locale.ROOT));
                capitalizeNext = false;
            } else {
                result.appendCodePoint(codePoint);
            }
            offset += Character.charCount(codePoint);
        }
        return result.toString();
    }

    /** Trims the text and replaces every run of whitespace with one space. */
    public static String removeDuplicateSpaces(String text) {
        requireText(text);
        return text.strip().replaceAll("\\s+", " ");
    }

    private static void requireText(String text) {
        if (text == null) {
            throw new IllegalArgumentException("text must not be null");
        }
    }
}

