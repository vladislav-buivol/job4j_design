package ru.job4j.lsp;

public interface Store<T> {
    void add(T item);

    void delete(int id);

    T get(int id);

    int size();
}
