package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public class InMemoryStoreHolder<T> implements StoresHolder<T> {
    private final List<Store<T>> storeList;


    public InMemoryStoreHolder() {
        this.storeList = new ArrayList<>();
    }

    @Override
    public List<T> extractAllFood() {
        List<T> foods = new ArrayList<>();
        for (Store<T> store : this.storeList) {
            foods.addAll(store.getAll());
        }
        return foods;
    }

    @Override
    public void clearStores() {
        for (Store<T> store : this.storeList) {
            store.clear();
        }
    }

    @Override
    public List<Store<T>> getStores() {
        return storeList;
    }

    @Override
    public void addStore(Store<T> store) {
        storeList.add(store);
    }
}
