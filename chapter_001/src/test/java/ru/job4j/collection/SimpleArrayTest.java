package ru.job4j.collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleArrayTest {

    @Test
    public void whenAddThenGet() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("first");
        String rsl = array.get(0);
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddThenIt() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("first");
        String rsl = array.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        SimpleArray<String> array = new SimpleArray<>();
        array.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("first");
        array.get(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleArray<String> array = new SimpleArray<>();
        array.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("first");
        Iterator<String> it = array.iterator();
        array.add("second");
        it.next();
    }

    @Test
    public void testArrayScalability() {
        SimpleArray<Integer> array = new SimpleArray<>();
        SimpleArray<Integer> array2 = new SimpleArray<>(1, 2, 3);
        for (int i = 0; i < 100; i++) {
            array.add(i);
            array2.add(i);
        }
        assertThat(array.size(), is(100));
        assertThat(array.get(array.size() - 1), is(99));
        System.out.println(array2.get(array2.size() - 1));
        assertThat(array2.get(array2.size() - 1), is(99));
    }

    @Test
    public void removeTest() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("1");
        array.add("2");
        array.add("3");
        array.add("4");
        array.remove(0);
        assertThat(array.get(0), is("2"));
        assertThat(array.get(1), is("3"));
        array.remove(2);
        assertThat(array.get(0), is("2"));
        assertThat(array.get(1), is("3"));
        assertThat(array.size(), is(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeErrorTest(){
        SimpleArray<String> array = new SimpleArray<>();
        array.add("1");
        array.add("2");
        array.add("3");
        array.remove(4);
    }


}