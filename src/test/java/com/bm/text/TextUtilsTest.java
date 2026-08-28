package com.bm.text;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TextUtilsTest {

    @Test
    void detectsPalindromesIgnoringPunctuationAndCase() {
        assertTrue(TextUtils.isPalindrome("A man, a plan, a canal: Panama!"));
        assertTrue(TextUtils.isPalindrome(""));
        assertFalse(TextUtils.isPalindrome("Java utilities"));
    }

    @Test
    void countsWordsSeparatedByAnyWhitespace() {
        assertEquals(4, TextUtils.countWords("  Java\tutilities\nare useful  "));
        assertEquals(0, TextUtils.countWords(" \t\n "));
    }

    @Test
    void reversesUnicodeTextWithoutBreakingCodePoints() {
        assertEquals("c😀ba", TextUtils.reverse("ab😀c"));
        assertEquals("", TextUtils.reverse(""));
    }

    @Test
    void capitalizesEachWordAndPreservesWhitespace() {
        assertEquals("Hello   Java\tWorld", TextUtils.capitalizeWords("hello   java\tworld"));
        assertEquals("", TextUtils.capitalizeWords(""));
    }

    @Test
    void normalizesWhitespace() {
        assertEquals("hello Java world", TextUtils.removeDuplicateSpaces("  hello\tJava\nworld  "));
        assertEquals("", TextUtils.removeDuplicateSpaces("  \t "));
    }

    @Test
    void rejectsNullInputConsistently() {
        assertThrows(IllegalArgumentException.class, () -> TextUtils.isPalindrome(null));
        assertThrows(IllegalArgumentException.class, () -> TextUtils.countWords(null));
        assertThrows(IllegalArgumentException.class, () -> TextUtils.reverse(null));
        assertThrows(IllegalArgumentException.class, () -> TextUtils.capitalizeWords(null));
        assertThrows(IllegalArgumentException.class, () -> TextUtils.removeDuplicateSpaces(null));
    }
}
