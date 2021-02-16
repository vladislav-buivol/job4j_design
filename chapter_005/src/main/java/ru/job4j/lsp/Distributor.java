package ru.job4j.lsp;

public interface Distributor<T> extends Resort<T> {
    void distribute(T t);
}
