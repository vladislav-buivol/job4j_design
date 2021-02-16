package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public abstract class FoodStore implements Store<Food> {
    List<Food> foods = new ArrayList<>();

    @Override
    public Food get(int i) {
        return foods.get(i);
    }

    @Override
    public List<Food> getAll() {
        return foods;
    }
}
