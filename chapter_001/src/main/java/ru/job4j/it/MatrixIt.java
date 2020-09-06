package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator for two dimensional array
 *
 * @author Vladislav Buivol
 * @since 06.09.2020
 */
public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;


    public MatrixIt(int[][] data) {
        this.data = data;
    }

    /**
     * Check that iterator has next element
     *
     * @return true, if iterator has next element, else false
     */
    @Override
    public boolean hasNext() {
        return hasNextRow() || hasNextColumn();
    }

    /**
     * @return the next element. Throws NoSuchElementException if there is no next element or if it is empty
     */
    @Override
    public Integer next() {
        if (hasNextColumn()) {
            return data[this.row][this.column++];
        } else if (hasNextRow()) {
            this.row++;
            if (data[row].length > 0) {
                this.column = 0;
                return data[row][this.column++];
            } else {
                return next();
            }
        }
        throw new NoSuchElementException();
    }

    private boolean hasNextRow() {
        return this.row < data.length - 1;
    }

    private boolean hasNextColumn() {
        return this.column < data[row].length;
    }
}
