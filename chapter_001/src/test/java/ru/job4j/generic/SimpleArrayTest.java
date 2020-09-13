package ru.job4j.generic;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleArrayTest {
    @Test
    public void add() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);
        simpleArray.add(1);
        checkElements(simpleArray, 1, null, null);
        simpleArray.add(2);
        checkElements(simpleArray, 1, 2, null);
        simpleArray.add(3);
        checkElements(simpleArray, 1, 2, 3);
        simpleArray.set(1, null);
        simpleArray.add(4);
        checkElements(simpleArray, 1, 4, 3);
        assertThat(simpleArray.add(4), is(false));
    }


    @Test
    public void removeAndAddElemTest() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);
        addElementsToArray(simpleArray, 1, 2, 3);
        simpleArray.remove(1);
        checkElements(simpleArray, 1, 3, null);
        simpleArray.add(4);
        simpleArray.remove(0);
        simpleArray.remove(2);
        checkElements(simpleArray, 3, 4, null);
    }

    @Test
    public void setElemTest() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);
        addElementsToArray(simpleArray, 1, 2, 3);
        simpleArray.set(0, null);
        simpleArray.set(2, 20);
        simpleArray.set(1, 30);
        checkElements(simpleArray, null, 30, 20);
    }

    @Test
    public void getLengthTest() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);
        addElementsToArray(simpleArray, 1, 2, 3);
        assertThat(simpleArray.getLength(), is(3));
    }

    @Test
    public void getElemTest() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);
        addElementsToArray(simpleArray, 1, 2, 3);
        assertThat(simpleArray.get(0), is(1));
        assertThat(simpleArray.get(1), is(2));
        assertThat(simpleArray.get(2), is(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setError() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);
        addElementsToArray(simpleArray, 1, 2, 3);
        simpleArray.set(4, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeError() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);
        addElementsToArray(simpleArray, 1, 2, 3);
        simpleArray.remove(5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getErrorTest() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);
        addElementsToArray(simpleArray, 1, 2, 3);
        simpleArray.get(5);
    }

    @Test
    public void iteratorTest() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);
        addElementsToArray(simpleArray, 1, 2, 3);
        Iterator<Integer> iterator = simpleArray.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorErrorTest() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);
        Iterator<Integer> iterator = simpleArray.iterator();
        for (int i = 0; i < simpleArray.getLength(); i++) {
            iterator.next();
        }
        assertThat(iterator.hasNext(), is(false));
        iterator.next();
    }


    private void addElementsToArray(SimpleArray<Integer> array, Integer... elementsToAdd) {
        for (int el : elementsToAdd) {
            array.add(el);
        }
    }

    private void checkElements(SimpleArray<Integer> array, Integer... elementsToCheck) {
        for (int i = 0; i < array.getLength(); i++) {
            if (i < elementsToCheck.length) {
                assertThat(array.get(i), is(elementsToCheck[i]));
            } else break;
        }
    }

}

