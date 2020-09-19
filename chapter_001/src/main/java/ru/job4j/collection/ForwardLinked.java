package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> last;

    public void add(T value) {
        Node<T> node = new Node<T>(null, value, null);
        if (head == null) {
            head = node;
            last = node;
            return;
        }
        Node<T> tail = head;
        tail.prev = null;
        while (tail.next != null) {
            Node<T> prev = tail;
            tail = tail.next;
            tail.prev = prev;
        }
        last = node;
        last.prev = tail;
        tail.next = node;
    }

    public T deleteFirst(){
        if(head == null){
            throw new NoSuchElementException();
        }
        Node<T> h = head;
        head = head.next;
        return h.value;
    }

    public T deleteLast(){
        if(last == null){
            throw new NoSuchElementException();
        }
        Node<T> l = last;
        last = last.prev;
        return l.value;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        Node<T> prev;
        T value;
        Node<T> next;

        public Node(Node<T> prev, T value, Node<T> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }
}