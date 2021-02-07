package ru.job4j.ood.isp.menu;

import java.util.List;

public interface Store<T> {

    void add(T t);

    T getById(String id);

    List<T> getAll();
}
