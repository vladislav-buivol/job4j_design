package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FlatMap<T> implements Iterator<T> {
    private final Iterator<Iterator<T>> data;
    private Iterator<T> cursor;

    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (!isCursorIsNullOrEmpty() && data.hasNext()) {
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

    /**
     * @return true, if cursor is not null and not empty
     */
    private boolean isCursorIsNullOrEmpty() {
        return cursor != null && cursor.hasNext();
    }
}