package com.bm.collections;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomHashMapTest {

    @Test
    public void testPutAndGet() {
        CustomHashMap<String, String> map = new CustomHashMap<>();
        map.put("name", "Bhagyashree");
        map.put("country", "Japan");

        assertEquals("Bhagyashree", map.get("name"));
        assertEquals("Japan", map.get("country"));
        assertNull(map.get("age"));
    }

    @Test
    public void testOverrideValue() {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();
        map.put("count", 1);
        map.put("count", 2); // override

        assertEquals(2, map.get("count"));
    }

    @Test
    public void testContainsKey() {
        CustomHashMap<String, Boolean> map = new CustomHashMap<>();
        map.put("exists", true);

        assertTrue(map.containsKey("exists"));
        assertFalse(map.containsKey("missing"));
    }
}
