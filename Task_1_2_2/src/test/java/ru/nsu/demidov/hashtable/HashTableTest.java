package ru.nsu.demidov.hashtable;

import java.util.Objects;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HashTableTest {
    @Test
    void hashTableTesting() throws IllegalAccessException {
        HashTable<String, Integer> sampleHashTable = new HashTable<>(100);
        sampleHashTable.put("one", 1);
        assertTrue(Objects.equals(1, (int) sampleHashTable.get("one")));
        sampleHashTable.update("one", 11);
        assertTrue(Objects.equals(sampleHashTable.get("one"), 11));
        sampleHashTable.put("two", 2);
        assertTrue(Objects.equals(sampleHashTable.toString(), "{two : 2; one : 11}"));
        sampleHashTable.remove("two");
        assertTrue(Objects.equals(sampleHashTable.get("two"), null));
        assertTrue(Objects.equals(sampleHashTable.toString(), "{one : 11}"));
        assertTrue(Objects.equals(sampleHashTable.containsKey("three"), false));
        StringBuilder str = new StringBuilder();
        for (HashTable.Pair<String, Integer> pair : sampleHashTable) {
            str.append(pair);
        }
        assertTrue(Objects.equals(str.toString(), "one : 11"));
    }
    
    @Test
    void setSmallHashTableTesting() throws IllegalAccessException {
        HashTable<String, Integer> smallHashTable = new HashTable<>(2);
        smallHashTable.put("one", 11);
        smallHashTable.put("two", 2);
        smallHashTable.put("three", 3);
        smallHashTable.put("four", 4);
        smallHashTable.remove("two");
        smallHashTable.remove("three");
        smallHashTable.remove("four");
        HashTable<String, Integer> sampleHashTable = new HashTable<>(100);
        sampleHashTable.put("one", 11);
        assertTrue(Objects.equals(sampleHashTable.equals(smallHashTable), true));

        HashTable<String, HashTable<Integer, HashTable<HashTable<HashTable<HashTable<String,
            HashTable<String, String>>, HashTable<String, HashTable<String,
            HashTable<String, String>>>>, HashTable<String, String>>,
            HashTable<String, String>>>> differentTypeHashTable = new HashTable<>(2);
        assertTrue(Objects.equals(sampleHashTable.equals(differentTypeHashTable), false));
    }
}
