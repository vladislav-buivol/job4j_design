package ru.job4j.lsp;

import java.util.List;

public interface Store<T> {
    void add(T item);

    boolean accept(T t);

    List<T> clear();

    int size();
}
