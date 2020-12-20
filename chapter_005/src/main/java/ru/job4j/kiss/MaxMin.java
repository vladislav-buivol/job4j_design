package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        Optional<T> o = value.stream().max(comparator);
        return o.isEmpty() ? null : o.get();
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        Optional<T> o = value.stream().min(comparator);
        return o.isEmpty() ? null : o.get();
    }

    public static class MaxMinComparator<T extends Comparable<T>> implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return o1.compareTo(o2);
        }
    }
}