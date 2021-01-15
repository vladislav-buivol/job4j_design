package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

import static ru.job4j.lsp.DateUtils.*;

public class Trash implements Store<Food> {
    List<Food> foods = new ArrayList<>();

    @Override
    public void add(Food food) {
        foods.add(food);
    }


    @Override
    public boolean accept(Food food) {
        return usedByPerCent(food) >= 1 && remainingDays(food.getExpiryDate()) < 0;
    }

    @Override
    public List<Food> clear() {
        List<Food> foodsCopy = new ArrayList<>(foods);
        foods.clear();
        return foodsCopy;
    }

    @Override
    public int size() {
        return foods.size();
    }
}
