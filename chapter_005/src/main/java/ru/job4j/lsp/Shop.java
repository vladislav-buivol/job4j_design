package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store<Food> {
    List<Food> foods = new ArrayList<>();

    @Override
    public void add(Food item) {
        foods.add(item);
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Food get(int id) {
        return null;
    }

    @Override
    public int size() {
        return foods.size();
    }
}
