package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return compare(value, comparator.reversed());
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return compare(value, comparator);
    }

    private <T> T compare(List<T> value, Comparator<T> comparator) {
        if (value == null || value.size() == 0) {
            return null;
        }
        T result = value.get(0);
        for (int i = 1; i < value.size(); i++) {
            if (comparator.compare(result, value.get(i)) > 0) {
                result = value.get(i);
            }
        }
        return result;
    }

    public static class MaxMinComparator<T extends Comparable<T>> implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return o1.compareTo(o2);
        }
    }
}