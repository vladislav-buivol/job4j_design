package ru.job4j.lsp;

import ru.job4j.lsp.utils.ControlQualityUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ControlQuality implements Distributor<Food> {
    private final List<Store<Food>> storeList;

    public ControlQuality(Store<Food>... stores) {
        storeList = Arrays.asList(stores);
    }

    public ControlQuality(List<Store<Food>> storeList) {
        this.storeList = storeList;
    }

    @Override
    public void distribute(Food food) {
        for (Store<Food> store : storeList) {
            if (store.accept(food)) {
                store.add(food);
                return;
            }
        }
    }

    @Override
    public List<Store<Food>> resort() {
        List<Food> foods = ControlQualityUtils.extractAllFood(storeList);
        ControlQualityUtils.clearStores(storeList);
        for (Food food : foods) {
            distribute(food);
        }
        return storeList;
    }
}
