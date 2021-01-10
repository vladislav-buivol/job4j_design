package ru.job4j.lsp;

public interface Strategy<T> {
    void doOperation(T t);
}
