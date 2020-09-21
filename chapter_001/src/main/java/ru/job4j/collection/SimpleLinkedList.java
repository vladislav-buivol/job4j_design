package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Linked list implementation
 *
 * @author Vladislav Buivol
 * @since 19.09.2020
 */
public class SimpleLinkedList<E> implements Iterable<E> {
    private int modCount = 0;
    int size = 0;
    Node<E> head = null;
    Node<E> last = null;


    public SimpleLinkedList() {
    }

    /**
     * Add element to the end of the list
     *
     * @param value - Value to add
     */
    public void add(E value) {
        Node<E> oldLast = last;
        Node<E> addedNode = new Node<E>(oldLast, value, null);
        last = addedNode;
        if (oldLast == null) {
            head = addedNode;
        } else {
            oldLast.next = addedNode;
        }
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
        Node<E> result = head;
        if (index < size / 2) {
            for (int i = 0; i != index; i++) {
                result = result.next;
            }
        } else {
            result = last;
            for (int i = size - 1; i != index; i--) {
                result = result.prev;
            }
        }
        return result.item;
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> node = head;
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
                Node<E> result = node;
                node = node.next;
                return result.item;
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

    @Override
    public String toString() {
        return "SimpleLinkedArray{" +
                "modCount=" + modCount +
                ", size=" + size +
                ", head=" + head +
                ", last=" + last +
                '}';
    }
}
