package ru.job4j.lsp;

public class FoodContext {
    private Strategy<Food> strategy;

    public FoodContext(Strategy<Food> strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy(Food food) {
        strategy.doOperation(food);
    }
}
