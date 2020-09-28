package ru.job4j.map;

import java.util.Iterator;

/**
 * HashMap list implementation
 *
 * @author Vladislav Buivol
 * @since 27.09.2020
 */
public class SimpleHashMap<K, V> {
    private static int DEFAULT_CONTAINER_SIZE = 16;
    private Entry<K, V> entry;

    public SimpleHashMap(int containerSize) {
        this.entry = new Entry<K, V>(containerSize);
    }

    public SimpleHashMap() {
        this.entry = new Entry<K, V>(DEFAULT_CONTAINER_SIZE);
    }

    /**
     * Insert object into map
     *
     * @param key   - key to find object
     * @param value - value to insert
     * @return true if object was added, else false
     */
    public boolean insert(K key, V value) {
        int hash = getHash(key);
        return entry.put(key, value, hash);

    }

    /**
     * Get object by key
     *
     * @param key - key to the object to get
     */
    public V get(K key) {
        return entry.get(getHash(key));
    }

    /**
     * Delete object by key from container
     *
     * @param key - key to the object to delete
     * @return true if object was deleted, else false
     */
    public boolean delete(K key) {
        int hash = getHash(key);
        return entry.delete(hash);

    }

    /**
     * Calculate hash for key
     *
     * @param key - key to object
     * @return hash for key
     */
    private int getHash(K key) {
        return (entry.size() - 1) & key.hashCode();
    }

    /**
     * @return iterator for keys
     */
    public Iterator<K> keysIterator() {
        return entry.keysIterator();
    }

    /**
     * @return iterator for values
     */
    public Iterator<V> valuesIterator() {
        return entry.valuesIterator();
    }

}
