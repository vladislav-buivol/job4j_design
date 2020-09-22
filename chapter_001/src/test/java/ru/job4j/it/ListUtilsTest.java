package ru.job4j.it;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2));
        ListUtils.addAfter(input, 1, 3);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
        ListUtils.addAfter(input, 2, 4);
        assertThat(Arrays.asList(1, 2, 3, 4), Is.is(input));
    }

    @Test
    public void removeAllTest() {
        List<String> input = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5"));
        List<String> elemToRemove = new ArrayList<>(Arrays.asList("1", "3", "5"));
        input = ListUtils.removeAll(input, elemToRemove);
        assertThat(input, Is.is(List.of("2", "4")));
    }

    @Test
    public void removeIfTest() {
        List<String> input = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "21"));
        Predicate<String> predicate = str -> str.startsWith("1");
        Predicate<String> predicate2 = str -> str.endsWith("1");
        Predicate<String> predicate3 = str -> str.contains("3");
        input = ListUtils.removeIf(input, predicate);
        assertThat(input, Is.is(List.of("2", "3", "4", "5", "21")));
        input = ListUtils.removeIf(input, predicate2);
        assertThat(input, Is.is(List.of("2", "3", "4", "5")));
        input = ListUtils.removeIf(input, predicate3);
        assertThat(input, Is.is(List.of("2", "4", "5")));
        input = ListUtils.removeIf(input, predicate3);
        assertThat(input, Is.is(List.of("2", "4", "5")));
    }


    @Test
    public void replaceIfTest() {
        List<String> input = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "21"));
        Predicate<String> predicate = str -> str.startsWith("1");
        Predicate<String> predicate2 = str -> str.endsWith("1");
        Predicate<String> predicate3 = str -> str.contains("3");
        input = ListUtils.replaceIf(input, predicate, "New1");
        assertThat(input, Is.is(List.of("New1", "2", "3", "4", "5", "21")));
        input = ListUtils.replaceIf(input, predicate2, "predicate2");
        assertThat(input, Is.is(List.of("predicate2", "2", "3", "4", "5", "predicate2")));
        input = ListUtils.replaceIf(input, predicate2, "anotherOne");
        assertThat(input, Is.is(List.of("predicate2", "2", "3", "4", "5", "predicate2")));
        input = ListUtils.replaceIf(input, predicate3, "three");
        assertThat(input, Is.is(List.of("predicate2", "2", "three", "4", "5", "predicate2")));
        input = ListUtils.replaceIf(input, predicate3, "NAN");
        assertThat(input, Is.is(List.of("predicate2", "2", "three", "4", "5", "predicate2")));
    }

}