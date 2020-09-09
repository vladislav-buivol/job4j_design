package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator that returns only even numbers
 *
 * @author Vladislav Buivol
 * @since 08.09.2020
 */
public class EvenIt implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public EvenIt(int[] data) {
        this.data = data;
    }

    /**
     * Check that iterator has next even element
     *
     * @return true, if iterator has next element, else false
     */
    @Override
    public boolean hasNext() {
        while (isValidPoint() && !isEvenNumber()) {
            point++;
        }
        return isValidPoint();
    }

    /**
     * @return the next even element. Throws NoSuchElementException if there is no next element or if it is empty
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point++];
    }

    /**
     * Check that number is even
     *
     * @return true, if number is even
     */
    private boolean isEvenNumber() {
        return data[point] % 2 == 0;
    }

    /**
     * Check that point is smaller than data length
     *
     * @return true if point is smaller
     */
    private boolean isValidPoint() {
        return point < data.length;
    }
}
