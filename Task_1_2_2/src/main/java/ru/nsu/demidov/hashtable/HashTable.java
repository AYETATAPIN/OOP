package ru.nsu.demidov.hashtable;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * HashTable class cruto ofigenno.
*/

public class HashTable<K, V> implements Iterable<HashTable.Pair<K, V>> {

    private static final float LOAD_FACTOR = 0.75f;
    private List<List<Pair<K, V>>> table;
    private int size;
    private int threshold;
    private int modCount;

    /**
     * HashTable constructor.
     */

    public HashTable(int capacity) {
        table = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            table.add(null);
        }
        threshold = (int) (capacity * LOAD_FACTOR);
        size = 0;
        modCount = 0;
    }

    private int hash(K key) {
        return key.hashCode() > 0 ? key.hashCode() % table.size()
                : (key.hashCode() % table.size()) * -1;
    }

    /**
     * HashTable put method.
     */

    public void put(K key, V value) throws IllegalArgumentException {
        if (size > threshold) {
            resize();
        }
        int index = hash(key);
        List<Pair<K, V>> bucket = table.get(index);
        if (bucket == null) {
            bucket = new LinkedList<>();
            table.set(index, bucket);
        }
        for (Pair<K, V> pair : bucket) {
            if (pair.key.equals(key)) {
                throw new IllegalArgumentException("Element " + pair.key + " : "
                    + pair.value + "override");
            }
        }
        bucket.add(new Pair<>(key, value));
        size++;
        modCount++;
    }

    /**
     * HashTable get method.
     */

    public V get(K key) {
        int index = hash(key);
        List<Pair<K, V>> bucket = table.get(index);
        if (bucket != null) {
            for (Pair<K, V> pair : bucket) {
                if (pair.key.equals(key)) {
                    return pair.value;
                }
            }
        }
        return null;
    }

    /**
     * HashTable remove method.
     */

    public void remove(K key) throws IllegalAccessException {
        int index = hash(key);
        List<Pair<K, V>> bucket = table.get(index);
        if (bucket != null) {
            Iterator<Pair<K, V>> iterator = bucket.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().key.equals(key)) {
                    iterator.remove();
                    size--;
                    modCount++;
                    return;
                }
            }
        }
        throw new IllegalAccessException("No such element with key" + key);
    }

    /**
     * HashTable update method.
     */

    public void update(K key, V value) {
        int index = hash(key);
        List<Pair<K, V>> bucket = table.get(index);
        if (bucket != null) {
            for (Pair<K, V> pair : bucket) {
                if (pair.key.equals(key)) {
                    pair.value = value;
                    return;
                }
            }
        }
        throw new IllegalArgumentException("No such element with key " + key);
    }

    /**
     * HashTable containsKey method.
     */

    public boolean containsKey(K key) {
        int index = hash(key);
        List<Pair<K, V>> bucket = table.get(index);
        if (bucket != null) {
            for (Pair<K, V> pair : bucket) {
                if (pair.key.equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * HashTable equals method.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        HashTable<K, V> comparable = (HashTable<K, V>) o;
        if (size != comparable.size) {
            return false;
        }
        for (List<Pair<K, V>> bucket : table) {
            if (bucket != null) {
                for (Pair<K, V> pair : bucket) {
                    if (comparable.containsKey(pair.key) == false
                        || Objects.equals(comparable.get(pair.key), pair.value) == false
                        || Objects.equals(this.hash(pair.key), comparable.hash(pair.key))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * HashTable toString method.
     */

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("{");
        for (List<Pair<K, V>> bucket : table) {
            if (bucket != null) {
                for (Pair<K, V> pair : bucket) {
                    str.append(pair.key).append(" : ").append(pair.value).append("; ");
                }
            }
        }
        if (str.length() > 1) {
            str.setLength(str.length() - 2);
        }
        str.append("}");
        return str.toString();
    }

    private void resize() {
        List<List<Pair<K, V>>> temp = table;
        table = new ArrayList<>(temp.size() * 2);
        for (int i = 0; i < temp.size() * 2; i++) {
            table.add(null);
        }
        threshold = (int) (table.size() * LOAD_FACTOR);
        size = 0;
        modCount++;
        for (List<Pair<K, V>> bucket : temp) {
            if (bucket != null) {
                for (Pair<K, V> pair : bucket) {
                    put(pair.key, pair.value);
                }
            }
        }
    }

    /**
     * HashTable pair class.
     */

    public static class Pair<K, V> {
        K key;
        V value;

        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return (key + " : " + value);
        }
    }

    /**
     * HashTable iterator.
     */

    @Override
    public Iterator<Pair<K, V>> iterator() {
        return new HashTableIterator();
    }

    private class HashTableIterator implements Iterator<Pair<K, V>> {

        private int bucketIndex;
        private int nodeIndex;
        private int initialModCount;

        private HashTableIterator() {
            initialModCount = modCount;
            bucketIndex = 0;
            nodeIndex = 0;
        }

        /**
         * Iterator hasNext method.
         */

        @Override
        public boolean hasNext() {
            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("You modificated the table while "
                    + "iteration. Your execution date is tomorrow");
            }
            for (int i = bucketIndex; i < table.size(); i++) {
                if (table.get(i) != null && table.get(i).isEmpty() == false) {
                    return true;
                }
            }
            return false;
        }

        /**
         * Iterator next method.
         */

        @Override
        public Pair<K, V> next() {
            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("You modificated the table while "
                    + "iteration. Your execution date is tomorrow");
            }
            while (bucketIndex < table.size()) {
                List<Pair<K, V>> bucket = table.get(bucketIndex);
                if (bucket != null && nodeIndex < bucket.size()) {
                    int temp = nodeIndex;
                    nodeIndex++;
                    if (nodeIndex == bucket.size()) {
                        bucketIndex++;
                        nodeIndex = 0;
                    }
                    return bucket.get(temp);
                }
                bucketIndex++;
                nodeIndex = 0;
            }
            return null;
        }
    }
}
