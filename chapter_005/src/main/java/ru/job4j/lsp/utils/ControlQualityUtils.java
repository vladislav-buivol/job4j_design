package ru.job4j.lsp.utils;

import ru.job4j.lsp.Food;
import ru.job4j.lsp.Store;

import java.util.ArrayList;
import java.util.List;

public class ControlQualityUtils {

    public static void clearStores(List<Store<Food>> storeList) {
        for (Store<Food> store : storeList) {
            store.clear();
        }
    }

    public static List<Food> extractAllFood(List<Store<Food>> storeList) {
        List<Food> foods = new ArrayList<>();
        for (Store<Food> store : storeList) {
            foods.addAll(store.getAll());
        }
        return foods;
    }
}
