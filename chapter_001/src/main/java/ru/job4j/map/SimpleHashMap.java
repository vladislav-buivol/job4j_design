package ru.job4j.map;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * HashMap list implementation
 *
 * @author Vladislav Buivol
 * @since 27.09.2020
 */
public class SimpleHashMap<K, V> {
    private int containerSize = 16;
    private static int DEFAULT_CONTAINER_SIZE = 16;
    private Entry<K, V>[] entry;
    private static final float LOAD_FACTORY = 0.75f;
    private int nrOfElements = 0;


    public SimpleHashMap(int containerSize) {
        this.entry = new Entry[containerSize];
    }

    public SimpleHashMap() {
        this.entry = new Entry[DEFAULT_CONTAINER_SIZE];
    }

    /**
     * Insert object into map
     *
     * @param key   - key to find object
     * @param value - value to insert
     * @return true if object was added, else false
     */
    public boolean insert(K key, V value) {
        resize();
        int hash = indexFor(key);
        if (entry[hash] == null) {
            entry[hash] = new Entry<>(key, value);
            nrOfElements++;
            return true;
        }
        return false;
    }

    /**
     * Get object by key
     *
     * @param key - key to the object to get
     */
    public V get(K key) {
        return (V) entry[indexFor(key)].value();
    }

    /**
     * Delete object by key from container
     *
     * @param key - key to the object to delete
     * @return true if object was deleted, else false
     */
    public boolean delete(K key) {
        int hash = indexFor(key);
        if (entry[hash] == null) {
            return false;
        }
        nrOfElements--;
        entry[hash] = null;
        return true;
    }

    /**
     * @return iterator for keys
     */
    public Iterator<K> keys() {
        return new Iterator<>() {
            private int point = 0;

            @Override
            public boolean hasNext() {
                while (point < containerSize && entry[point] == null) {
                    point++;
                }
                return point < containerSize;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (K) entry[point++].key();
            }
        };
    }

    /**
     * @return iterator for values
     */
    public Iterator<V> values() {
        return new Iterator<>() {
            private int point = 0;

            @Override
            public boolean hasNext() {
                while (point < containerSize && entry[point] == null) {
                    point++;
                }
                return point < containerSize;
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (V) entry[point++].value();
            }
        };
    }

    private int indexFor(K key) {
        if (key == null) {
            return 0;
        }
        return hash(key.hashCode()) & (containerSize - 1);
    }

    private int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    private void resize() {
        if (nrOfElements >= containerSize * LOAD_FACTORY) {
            this.containerSize = this.containerSize * 2;
            Entry<K, V>[] newEntry = new Entry[containerSize];
            System.arraycopy(entry, 0, newEntry, 0, nrOfElements);
            entry = newEntry;
        }
    }

    @Override
    public String toString() {
        return "SimpleHashMap{" +
                "containerSize=" + containerSize +
                ", entry=" + Arrays.toString(entry) +
                ", nrOfElements=" + nrOfElements +
                '}';
    }
}
