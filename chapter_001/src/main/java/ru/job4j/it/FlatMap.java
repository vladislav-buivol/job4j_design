package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator that returns items from sub iterators
 *
 * @author Vladislav Buivol
 * @since 13.09.2020
 */
public class FlatMap<T> implements Iterator<T> {
    private final Iterator<Iterator<T>> data;
    private Iterator<T> cursor;

    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
        if (data.hasNext()) {
            cursor = data.next();
        }
    }

    @Override
    public boolean hasNext() {
        while (data.hasNext() && !cursor.hasNext()) {
            cursor = data.next();
        }
        return cursor.hasNext();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return cursor.next();
    }
}