package ru.job4j.collection;

public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<T>();

    /**
     * Remove element and return it from stack
     *
     * @return deleted element
     */
    public T pop() {
        return linked.deleteLast();
    }

    /**
     * Add element at the end
     *
     * @param value - element to add
     */
    public void push(T value) {
        linked.add(value);
    }

}