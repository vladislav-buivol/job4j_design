package ru.job4j.generic;


import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Universal wrapper over the array
 *
 * @author Vladislav Buivol
 * @since 13.09.2020
 */
public class SimpleArray<T> {
    private final int length;
    private final T[] data;


    public SimpleArray(int length) {
        this.length = length;
        this.data = (T[]) new Object[length];
    }

    /**
     * Add the specified element (model) to the first available cell
     *
     * @param model - element to add
     * @return true if element was added, else false
     */
    public boolean add(T model) {
        int indexToAdd = getFirstNullElemIndex();
        if (indexToAdd != -1) {
            data[indexToAdd] = model;
            return true;
        }
        return false;
    }

    /**
     * Replace the specified element with the element at index
     *
     * @param index - element index
     * @param model - element to set
     */
    public void set(int index, T model) {
        checkIndex(index);
        data[index] = model;
    }


    /**
     * Remove the element at the specified index and shift all other after the specified index to left
     *
     * @param index - index of the element to remove
     */
    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < length - 1; i++) {
            data[i] = data[i + 1];
        }
        data[length - 1] = null;

    }

    /**
     * @param index of element
     * @return element at the specified index
     */
    public T get(int index) {
        checkIndex(index);
        return data[index];
    }

    /**
     * @return iterator
     */
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int point = 0;

            @Override
            public boolean hasNext() {
                return point < length;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return data[point++];
            }
        };
    }

    /**
     * @return index of the first null element found. If there no null element, then return -1
     */
    private int getFirstNullElemIndex() {
        for (int i = 0; i < this.length; i++) {
            if (data[i] == null) return i;
        }
        return -1;
    }

    /**
     * Check that index is smaller than the array length
     *
     * @param index to check
     */
    private void checkIndex(int index) {
        Objects.checkIndex(index, length);
    }


    public int getLength() {
        return length;
    }

}
