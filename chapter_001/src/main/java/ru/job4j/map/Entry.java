package ru.job4j.map;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Entry<K, V> {
    private int containerSize = 16;
    private static final float LOAD_FACTORY = 0.75f;
    private Object[] keys;
    private Object[] values;
    private int nrOfElements = 0;

    public Entry(int containerSize) {
        this.containerSize = containerSize;
        keys = new Object[containerSize];
        values = new Object[containerSize];
    }

    public Entry() {
        keys = new Object[containerSize];
        values = new Object[containerSize];
    }

    public V get(int index) {
        if (containerSize > index) {
            return (V) values[index];
        }
        return null;
    }

    public boolean put(K key, V value, int index) {
        doubleContainer();
        if (keys[index] != null || values[index] != null) {
            return false;
        }
        keys[index] = key;
        values[index] = value;
        nrOfElements++;
        return true;
    }

    public boolean delete(int index) {
        if (keys[index] == null || values[index] == null) {
            return false;
        }
        keys[index] = null;
        values[index] = null;
        nrOfElements--;
        return true;
    }

    public K[] keys() {
        return (K[]) keys;
    }

    public V[] values() {
        return (V[]) values;
    }

    public Iterator<K> keysIterator() {
        return new Iterator<K>() {
            private int point = 0;

            @Override
            public boolean hasNext() {
                while (keys[point] == null) {
                    point++;
                }
                return point < containerSize;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (K) keys[point++];
            }
        };
    }

    public Iterator<V> valuesIterator() {
        return new Iterator<V>() {
            private int point = 0;

            @Override
            public boolean hasNext() {
                while (values[point] == null) {
                    point++;
                }
                return point < containerSize;
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (V) values[point++];
            }
        };
    }

    private void doubleContainer() {
        if (nrOfElements >= containerSize * LOAD_FACTORY) {
            this.containerSize = this.containerSize * 2;
            Object[] newKeys = new Object[containerSize];
            Object[] newValues = new Object[containerSize];
            System.arraycopy(keys, 0, newKeys, 0, nrOfElements);
            System.arraycopy(values, 0, newValues, 0, nrOfElements);
            keys = newKeys;
            values = newValues;
        }
    }

    public int size() {
        return containerSize;
    }
}
