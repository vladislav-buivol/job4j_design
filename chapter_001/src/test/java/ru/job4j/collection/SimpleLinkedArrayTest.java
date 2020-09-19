package ru.job4j.collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleLinkedArrayTest {

    @Test
    public void whenAddThenGet() {
        SimpleLinkedArray<String> array = new SimpleLinkedArray<>();
        array.add("first");
        String rsl = array.get(0);
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddThenIt() {
        SimpleLinkedArray<String> array = new SimpleLinkedArray<>();
        array.add("first");
        String rsl = array.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        SimpleLinkedArray<String> array = new SimpleLinkedArray<>();
        array.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        SimpleLinkedArray<String> array = new SimpleLinkedArray<>();
        array.add("first");
        array.get(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleLinkedArray<String> array = new SimpleLinkedArray<>();
        array.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleLinkedArray<String> array = new SimpleLinkedArray<>();
        array.add("first");
        Iterator<String> it = array.iterator();
        array.add("second");
        it.next();
    }

    @Test
    public void testArrayScalability() {
        SimpleLinkedArray<Integer> array = new SimpleLinkedArray<>();
        for (int i = 0; i < 100; i++) {
            array.add(i);
        }
        assertThat(array.get(0), is(0));
        assertThat(array.get(50), is(50));
        assertThat(array.get(99), is(99));
        assertThat(array.size, is(100));
    }

}
