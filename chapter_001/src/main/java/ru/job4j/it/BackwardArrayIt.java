package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator for arrays that returns elements in backward order
 *
 * @author Vladislav Buivol
 * @since 06.09.2020
 */
public class BackwardArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int point;

    public BackwardArrayIt(int[] data) {
        this.data = data;
        this.point = data.length - 1;
    }

    /**
     * Check that iterator has next element in backward order
     *
     * @return true, if iterator has next element, else false
     */
    @Override
    public boolean hasNext() {
        return point > -1;
    }

    /**
     * @return the next element in backward order
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point--];
    }
}
