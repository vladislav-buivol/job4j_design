package ru.job4j.design.srp;

import java.util.Collection;
import java.util.Comparator;

public interface IReport<T> {
    void add(T elem);
    T delete(int i);

    Collection<String> getReport();

    Collection<T> sort(Comparator<T> comparator);
}
