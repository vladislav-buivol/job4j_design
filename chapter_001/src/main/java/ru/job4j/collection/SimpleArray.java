package ru.job4j.collection;

import java.util.*;

/**
 * Dynamic list realization
 *
 * @author Vladislav Buivol
 * @since 17.09.2020
 */
public class SimpleArray<T> implements Iterable<T> {
    private Object[] container;
    private int modCount = 0;
    private int nrOfElements = 0;
    private int containerSize = 10;

    public SimpleArray(T... container) {
        for (T t : container) {
            if (t != null) {
                nrOfElements++;
            }
        }
        this.container = container;
        this.containerSize = container.length;
    }

    public SimpleArray() {
        this.container = new Object[containerSize];
    }

    public T get(int index) {
        checkIndex(index);
        return (T) container[index];
    }

    public void add(T model) {
        doubleContainerSize();
        modCount++;
        container[nrOfElements++] = model;
    }

    public void remove(int index) {
        checkIndex(index);
        modCount++;
        System.arraycopy(container, index + 1, container, index, nrOfElements - index - 1);
        container[nrOfElements--] = null;
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {
            final int expectedModCount = modCount;
            int point = 0;

            @Override
            public boolean hasNext() {
                return point < nrOfElements;
            }

            @Override
            public T next() {
                checkModCount(expectedModCount);
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) container[point++];
            }
        };
    }

    @Override
    public String toString() {
        return "SimpleArray{" +
                "container=" + Arrays.toString(container) +
                ", modCount=" + modCount +
                '}';
    }

    /**
     * @return number of elements in list
     */
    public int size() {
        return nrOfElements;
    }

    /**
     * Check that index doesn't exceed the number of elements
     *
     * @param index - index to check
     */
    private void checkIndex(int index) {
        Objects.checkIndex(index, nrOfElements);
    }

    /**
     * Check that number of modification has not changed
     *
     * @param expectedModCount - expected number of modifications
     */
    private void checkModCount(int expectedModCount) {
        if (expectedModCount != modCount) {
            throw new ConcurrentModificationException();
        }
    }

    /**
     * Double container size
     */
    private void doubleContainerSize() {
        if (nrOfElements >= containerSize) {
            containerSize = containerSize * 2;
            Object[] newContainer = new Object[containerSize];
            System.arraycopy(container, 0, newContainer, 0, nrOfElements);
            container = newContainer;
        }
    }
}