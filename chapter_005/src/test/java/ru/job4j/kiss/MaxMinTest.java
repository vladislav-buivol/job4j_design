package ru.job4j.kiss;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class MaxMinTest {
    List<Integer> intLs = new ArrayList<>();
    List<String> strLs = new ArrayList<>();

    @Before
    public void init() {
        String[] testString = new String[]{"a", "aa", "aaa", "aab"};
        for (int i = 0; i < 10; i++) {
            intLs.add(i);
        }
        strLs.addAll(Arrays.asList(testString));
        Collections.shuffle(intLs);
        Collections.shuffle(strLs);
    }

    @Test
    public void maxMinTest() {
        MaxMin maxMin = new MaxMin();
        Integer max = maxMin.max(intLs, new MaxMin.MaxMinComparator<>());
        Integer min = maxMin.min(intLs, new MaxMin.MaxMinComparator<>());
        String biggerStr = maxMin.max(strLs, new MaxMin.MaxMinComparator<>());
        String smallerStr = maxMin.min(strLs, new MaxMin.MaxMinComparator<>());
        assertThat(max, equalTo(9));
        assertThat(min, equalTo(0));
        assertThat(biggerStr, equalTo("aab"));
        assertThat(smallerStr, equalTo("a"));
    }

}