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

    public static void main(String[] args) {
        int[][] in = {
                {}, {1}
        };
        MatrixIt it = new MatrixIt(in);
        System.out.println(it.hasNext());
    }

    /**
     * Check that iterator has next element
     *
     * @return true, if iterator has next element, else false
     */
    @Override
    public boolean hasNext() {
        if (hasNextColumn()) {
            return true;
        }
        this.column = 0;
        skipEmptyElements();
        return hasNextRow();
    }

    /**
     * Increase the row pointer while it is empty
     */
    private void skipEmptyElements() {
        do {
            this.row++;
        } while (hasNextRow() && isNextRowEmpty());
    }

    /**
     * Check if next row empty.
     *
     * @return false if row not empty, else returns true
     */
    private boolean isNextRowEmpty() {
        return data[row].length == 0;
    }

    /**
     * @return the next element. Throws NoSuchElementException if there is no next element or if it is empty
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }

    /**
     * @return true, if matrix has next row, else return false
     */
    private boolean hasNextRow() {
        return this.row < data.length;
    }

    /**
     * @return true if matrix has next column, else return false
     */
    private boolean hasNextColumn() {
        return this.column < data[row].length;
    }
}
