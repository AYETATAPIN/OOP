package ru.nsu.demidov.hashtable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class HashTableTest {

    private HashTable<String, Integer> sampleHashTable;
    private HashTable<String, Integer> smallHashTable;

    @BeforeEach
    void initEach() {
        sampleHashTable = new HashTable<>(100);
        smallHashTable = new HashTable<>(2);
    }

    @Test
    void testPutAndGet() {
        sampleHashTable.put("one", 1);
        assertEquals(1, (int) sampleHashTable.get("one"));
    }

    @Test
    void testUpdate() {
        sampleHashTable.put("one", 1);
        sampleHashTable.update("one", 11);
        assertEquals(11, (int) sampleHashTable.get("one"));
    }

    @Test
    void testToStringAfterPut() {
        sampleHashTable.put("one", 11);
        sampleHashTable.put("two", 2);
        assertEquals("{two : 2; one : 11}", sampleHashTable.toString());
    }

    @Test
    void testRemove() throws IllegalAccessException {
        sampleHashTable.put("one", 11);
        sampleHashTable.put("two", 2);
        sampleHashTable.remove("two");
        assertNull(sampleHashTable.get("two"));
    }

    @Test
    void testToStringAfterRemove() throws IllegalAccessException {
        sampleHashTable.put("one", 11);
        sampleHashTable.put("two", 2);
        sampleHashTable.remove("two");
        assertEquals("{one : 11}", sampleHashTable.toString());
    }

    @Test
    void testContainsKey() {
        sampleHashTable.put("one", 11);
        assertFalse(sampleHashTable.containsKey("three"));
        assertTrue(sampleHashTable.containsKey("one"));
    }

    @Test
    void testIterator() {
        sampleHashTable.put("one", 11);
        StringBuilder str = new StringBuilder();
        for (HashTable.Pair<String, Integer> pair : sampleHashTable) {
            str.append(pair);
        }
        assertEquals("one : 11", str.toString());
    }

    @Test
    void testSmallHashTablePutAndRemove() throws IllegalAccessException {
        smallHashTable.put("one", 11);
        smallHashTable.put("two", 2);
        smallHashTable.put("three", 3);
        smallHashTable.put("four", 4);
        smallHashTable.remove("two");
        smallHashTable.remove("three");
        smallHashTable.remove("four");
        sampleHashTable.put("one", 11);
        assertEquals(sampleHashTable, smallHashTable);
    }

    @Test
    void testDifferentTypeHashTable() {
        sampleHashTable.put("one", 11);
        HashTable<String, HashTable<Integer, HashTable<HashTable<HashTable<HashTable<String,
                HashTable<String, String>>, HashTable<String, HashTable<String,
                HashTable<String, String>>>>, HashTable<String, String>>,
                HashTable<String, String>>>> differentTypeHashTable = new HashTable<>(2);
        assertNotEquals(sampleHashTable, differentTypeHashTable);
    }
}