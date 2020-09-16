package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        int elemIndex = findElementIndex(id);
        if (elemIndex == -1) {
            return false;
        }
        mem.set(elemIndex, model);
        return true;
    }

    @Override
    public boolean delete(String id) {
        int elemIndex = findElementIndex(id);
        if (elemIndex == -1) {
            return false;
        }
        mem.remove(elemIndex);
        return true;
    }

    @Override
    public T findById(String id) {
        int elemIndex = findElementIndex(id);
        if (elemIndex == -1) {
            return null;
        }
        return mem.get(elemIndex);
    }

    public int size() {
        return mem.size();
    }

    private int findElementIndex(String elemId) {
        for (int i = 0; i < mem.size(); i++) {
            if (mem.get(i).getId().equals(elemId)) {
                return i;
            }
        }
        return -1;
    }
}