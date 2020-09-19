package ru.job4j.collection;

import java.util.*;

/**
 * Linked list implementation
 *
 * @author Vladislav Buivol
 * @since 19.09.2020
 */
public class SimpleLinkedArray<E> implements Iterable<E> {
    Object[] container;
    private int length = 10;
    private int modCount = 0;
    int size = 0;
    Node<E> first = null;
    Node<E> last = null;


    public SimpleLinkedArray() {
        this.container = new Object[length];
    }

    /**
     * Add element to the end of the list
     *
     * @param value - Value to add
     */
    public void add(E value) {
        doubleContainerSize();
        Node<E> l = last;
        Node<E> newNode = new Node<E>(last, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        container[size] = newNode;
        size++;
        modCount++;
    }

    /**
     * Get value by index
     *
     * @param index - index of element
     * @return value
     */
    public E get(int index) {
        checkIndex(index);
        return (E) ((Node) container[index]).item;
    }


    @Override
    public Iterator<E> iterator() {
        SimpleLinkedArray<E> thisArray = this;
        return new Iterator<E>() {
            private int point = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return point < size;
            }

            @Override
            public E next() {
                checkMod(expectedModCount);
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return thisArray.get(point++);
            }
        };
    }

    private static class Node<E> {
        public E item;
        public Node<E> next;
        public Node<E> prev;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * Check that index doesn't exceed the number of elements
     *
     * @param index - index to check
     */
    private void checkIndex(int index) {
        Objects.checkIndex(index, size);
    }

    /**
     * Check that number of modification has not changed
     *
     * @param expectedModCount - expected number of modifications
     */
    private void checkMod(int expectedModCount) {
        if (expectedModCount != modCount) {
            throw new ConcurrentModificationException();
        }
    }

    /**
     * Double container size
     */
    private void doubleContainerSize() {
        if (size >= length) {
            length = length * 2;
            Object[] newContainer = new Object[length];
            System.arraycopy(container, 0, newContainer, 0, size);
            container = newContainer;
        }
    }

    @Override
    public String toString() {
        return "SimpleLinkedArray{" +
                "container=" + Arrays.toString(container) +
                ", length=" + length +
                ", modCount=" + modCount +
                ", size=" + size +
                ", first=" + first +
                ", last=" + last +
                '}';
    }
}
