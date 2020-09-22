package ru.job4j.collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Simple set implementation
 *
 * @author Vladislav Buivol
 * @since 22.09.2020
 */
public class SimpleSetTest {
    @Test
    public void addTest() {
        SimpleSet<Integer> set = new SimpleSet<>();
        for (int i = 0; i < 20; i++) {
            set.add(i);
        }
        set.add(1);
        set.add(2);
        set.add(3);
        assertThat(set.getSize(), is(20));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void iteratorErrorTest() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            set.add(4);
        }
    }

    @Test
    public void iteratorTest() {
        SimpleSet<Integer> set = new SimpleSet<>();
        for (int i = 0; i < 3; i++) {
            set.add(i);
            set.add(i);
            set.add(i);
        }
        Iterator<Integer> iterator = set.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            assertThat(iterator.next(), is(i));
            i++;
        }
    }
}
