package ru.job4j.map;

import ru.job4j.generic.SimpleArray;

import java.util.Iterator;

/**
 * HashMap list implementation
 *
 * @author Vladislav Buivol
 * @since 27.09.2020
 */
public class SimpleHashMap<K, V> {
    private int nrOfElements = 0;
    private int containerSize = 16;
    private static float LOAD_FACTORY = 0.75f;
    Object[] values = new Object[containerSize];
    SimpleArray<K> keys = new SimpleArray<K>(containerSize);


    /**
     * Insert object into map
     *
     * @param key   - key to find object
     * @param value - value to insert
     * @return true if object was added, else false
     */
    public boolean insert(K key, V value) {
        doubleContainerSize();
        int hash = getHash(key);
        if (values[hash] == null) {
            values[hash] = value;
            keys.add(key);
            nrOfElements++;
            return true;
        }
        return false;
    }

    /**
     * Get object by key
     *
     * @param key - key to the object to get
     * @return
     */
    public V get(K key) {
        return (V) values[getHash(key)];
    }

    /**
     * Delete object by key from container
     *
     * @param key - key to the object to delete
     * @return true if object was deleted, else false
     */
    public boolean delete(K key) {
        int hash = getHash(key);
        if (values[hash] != null) {
            values[hash] = null;
            nrOfElements--;
            return true;
        }
        return false;
    }

    /**
     * Calculate hash for key
     *
     * @param key - key to object
     * @return hash for key
     */
    private int getHash(K key) {
        return (values.length - 1) & key.hashCode();
    }

    /**
     * @return iterator for keys
     */
    public Iterator<K> iterator() {
        return keys.iterator();
    }


    /**
     * Double container size
     */
    private void doubleContainerSize() {
        if (nrOfElements >= containerSize * LOAD_FACTORY) {
            containerSize = containerSize * 2;
            Object[] newContainerValues = new Object[containerSize];
            System.arraycopy(values, 0, newContainerValues, 0, nrOfElements);
            values = newContainerValues;
        }
    }
}
