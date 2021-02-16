package ru.job4j.lsp;

import java.util.List;

public interface Resort<T> {
    List<Store<T>> resort();
}

