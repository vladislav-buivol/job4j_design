package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {
    private Object[] container;
    private int containerInitSize = 10;
    private int size = 0;
    private int modCount = 0;

    public SimpleSet() {
        this.container = new Object[containerInitSize];
    }

    public void add(E e) {
        doubleContainerSize();
        if (contains(e)) {
            return;
        }
        modCount++;
        container[size++] = e;
    }

    @Override
    public Iterator<E> iterator() {
        int expectedModCount = modCount;
        return new Iterator<E>() {
            private int point = 0;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public E next() {
                return (E) container[point++];
            }
        };
    }

    private void doubleContainerSize() {
        if (size >= container.length) {
            Object[] biggerContainer = new Object[container.length * 2];
            System.arraycopy(container, 0, biggerContainer, 0, size);
            container = biggerContainer;
        }
    }

    private boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (e.equals(container[i])) {
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        return size;
    }
}
