package ru.job4j.collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleLinkedListTest {

    @Test
    public void whenAddThenGet() {
        SimpleLinkedList<String> array = new SimpleLinkedList<>();
        array.add("first");
        String rsl = array.get(0);
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddThenIt() {
        SimpleLinkedList<String> array = new SimpleLinkedList<>();
        array.add("first");
        String rsl = array.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        SimpleLinkedList<String> array = new SimpleLinkedList<>();
        array.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        SimpleLinkedList<String> array = new SimpleLinkedList<>();
        array.add("first");
        array.get(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleLinkedList<String> array = new SimpleLinkedList<>();
        array.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleLinkedList<String> array = new SimpleLinkedList<>();
        array.add("first");
        Iterator<String> it = array.iterator();
        array.add("second");
        it.next();
    }

    @Test
    public void testArrayScalability() {
        SimpleLinkedList<Integer> array = new SimpleLinkedList<>();
        for (int i = 0; i < 100; i++) {
            array.add(i);
        }
        assertThat(array.get(0), is(0));
        assertThat(array.get(48), is(48));
        assertThat(array.get(49), is(49));
        assertThat(array.get(50), is(50));
        assertThat(array.get(51), is(51));
        assertThat(array.get(52), is(52));
        assertThat(array.get(99), is(99));
        assertThat(array.size, is(100));
    }

}
