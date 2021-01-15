package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

import static ru.job4j.lsp.DateUtils.remainingDays;
import static ru.job4j.lsp.DateUtils.usedByPerCent;

public class Shop implements Store<Food> {
    List<Food> foods = new ArrayList<>();

    @Override
    public void add(Food food) {
        long remainingDays = remainingDays(food.getExpiryDate());
        double spent = usedByPerCent(food);
        if (isaSetDiscount(remainingDays, spent)) {
            foods.add(food);
            food.setDiscount(0.5);
        } else {
            foods.add(food);
        }
    }

    private boolean isaSetDiscount(long remainingDays, double spent) {
        return spent >= 0.75 && remainingDays > 0;
    }

    @Override
    public boolean accept(Food food) {
        double used = usedByPerCent(food);
        return used > 0.25 && used < 1 && remainingDays(food.getExpiryDate()) > 0;
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
