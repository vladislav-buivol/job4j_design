package ru.job4j.lsp;

import java.util.List;

public interface StoresHolder<T> {
    List<T> extractAllFood();

    void clearStores();

    List<Store<T>> getStores();

    void addStore(Store<T> store);
}
