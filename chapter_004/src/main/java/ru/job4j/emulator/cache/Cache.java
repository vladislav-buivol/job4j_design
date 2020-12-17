package ru.job4j.emulator.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Objects;

public abstract class Cache<K, V> {
    private SoftReference<HashMap<K, V>> cache;

    public Cache() {
        init();
    }

    void init() {
        if (cache == null || cache.get() == null) {
            cache = new SoftReference<>(new HashMap<>());
        }
    }

    /**
     * Find an object by parameters
     *
     * @param key
     * @return object that match
     */
    public V get(K key) {
        init();
        V value = Objects.requireNonNull(cache.get()).get(key);
        if (value == null) {
            load(key);
        }
        return Objects.requireNonNull(cache.get()).get(key);
    }

    /**
     * Add object/objects to cache
     *
     * @param key
     * @param value
     */

    public void add(K key, V value) {
        init();
        Objects.requireNonNull(cache.get()).put(key, value);
    }

    /**
     * Delete object from cache
     *
     * @param key - object to delete
     * @return true if object deleted
     */
    public boolean delete(K key) {
        init();
        V s = Objects.requireNonNull(cache.get()).remove(key);
        return s != null;
    }

    /**
     * Load object to cache
     *
     * @param file
     */
    public abstract void load(K file);

}
