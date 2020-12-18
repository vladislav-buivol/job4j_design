package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        sort(value, comparator);
        return value.get(0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        sort(value, comparator.reversed());
        return value.get(0);
    }

    private <T> void sort(List<T> value, Comparator<T> comparator) {
        value.sort((o1, o2) -> comparator.compare(o2, o1));
    }

    public static class MaxMinComparator<T extends Comparable<T>> implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return o1.compareTo(o2);
        }
    }
}