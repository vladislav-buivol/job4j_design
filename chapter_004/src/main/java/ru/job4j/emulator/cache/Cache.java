package ru.job4j.emulator.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;

public abstract class Cache<K, V> {
    private HashMap<K, SoftReference<V>> cache = new HashMap<>();

    public Cache() {

    }

    /**
     * Find an object by parameters
     *
     * @param key
     * @return object that match
     */
    public V get(K key) {
        if (cache.get(key) != null) {
            V result = cache.get(key).get();
            if (result != null) {
                return result;
            }
        }
        return load(key);
    }

    /**
     * Add object/objects to cache
     *
     * @param key
     * @param value
     */

    public void add(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    /**
     * Delete object from cache
     *
     * @param key - object to delete
     * @return true if object deleted
     */
    public boolean delete(K key) {
        V value = cache.remove(key).get();
        return value != null;
    }

    /**
     * Load object to cache
     *
     * @param file
     */
    public abstract V load(K file);

}
