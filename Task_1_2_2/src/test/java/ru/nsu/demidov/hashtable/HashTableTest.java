package ru.nsu.demidov.hashtable;

import java.util.Objects;
import org.junit.jupiter.api.Test;

class HashTableTest {
    @Test
    void hashTableTesting() throws IllegalAccessException {
        HashTable<String, Integer> sampleHashTable = new HashTable<>(100);
        sampleHashTable.put("one", 1);
        assert (Objects.equals(sampleHashTable.get("one"), 1));
        sampleHashTable.update("one", 11);
        assert (Objects.equals(sampleHashTable.get("one"), 11));
        sampleHashTable.put("two", 2);
        assert (Objects.equals(sampleHashTable.toString(), "{two : 2; one : 11}"));
        sampleHashTable.remove("two");
        assert (Objects.equals(sampleHashTable.get("two"), null));
        assert (Objects.equals(sampleHashTable.toString(), "{one : 11}"));
        assert (Objects.equals(sampleHashTable.containsKey("three"), false));
        StringBuilder str = new StringBuilder();
        for (HashTable.Pair<String, Integer> pair : sampleHashTable) {
            str.append(pair);
        }
        assert (Objects.equals(str.toString(), "one : 11"));
        HashTable<String, Integer> smallHashTable = new HashTable<>(2);
        smallHashTable.put("one", 11);
        smallHashTable.put("two", 2);
        smallHashTable.put("three", 3);
        smallHashTable.put("four", 4);
        smallHashTable.remove("two");
        smallHashTable.remove("three");
        smallHashTable.remove("four");
        assert (Objects.equals(sampleHashTable.equals(smallHashTable), true));

        HashTable<String, HashTable<String, HashTable<HashTable<HashTable<HashTable<String,
            HashTable<String, String>>, HashTable<String, HashTable<String,
            HashTable<String, String>>>>, HashTable<String, String>>,
            HashTable<String, String>>>> differentTypeHashTable = new HashTable<>(2);
        assert (Objects.equals(sampleHashTable.equals(differentTypeHashTable), false));


    }
}
