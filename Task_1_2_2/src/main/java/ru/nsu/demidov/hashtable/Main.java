package ru.nsu.demidov.hashtable;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        HashTable<String, Integer> hashTable = new HashTable<>(100);
        hashTable.put("one", 1);
        hashTable.update("one", 11);
        System.out.println(hashTable.get("one"));
        hashTable.put("two", 2);
        hashTable.put("three", 3);
        System.out.println(hashTable);
        hashTable.remove("two");
        System.out.println(hashTable);
        System.out.println(hashTable.containsKey("three"));
        for (HashTable.Pair<String, Integer> pair : hashTable) {
            System.out.println(pair);
        }
    }
}
