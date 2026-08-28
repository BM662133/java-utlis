package com.bm.collections;

public class CustomHashMap<K, V> {
    private static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int SIZE = 16;
    private Entry<K, V>[] table;

    @SuppressWarnings("unchecked")
    public CustomHashMap() {
        table = new Entry[SIZE];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % SIZE;
    }

    public void put(K key, V value) {
        int idx = hash(key);
        Entry<K, V> node = table[idx];

        if (node == null) {
            table[idx] = new Entry<>(key, value);
            return;
        }

        while (true) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
            if (node.next == null) break;
            node = node.next;
        }
        node.next = new Entry<>(key, value);
    }

    public V get(K key) {
        int idx = hash(key);
        Entry<K, V> node = table[idx];
        while (node != null) {
            if (node.key.equals(key)) return node.value;
            node = node.next;
        }
        return null;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }
}