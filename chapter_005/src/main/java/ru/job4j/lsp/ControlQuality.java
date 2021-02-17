package ru.job4j.lsp;

import java.util.List;

public class ControlQuality implements Distributor<Food> {
    private final StoresHolder<Food> storeList;

    public ControlQuality(Store<Food>... stores) {
        storeList = new InMemoryStoreHolder<>();
        for (Store<Food> store : stores) {
            storeList.addStore(store);
        }
    }

    public ControlQuality(StoresHolder<Food> storeList) {
        this.storeList = storeList;
    }

    @Override
    public void distribute(Food food) {
        for (Store<Food> store : storeList.getStores()) {
            if (store.accept(food)) {
                store.add(food);
                return;
            }
        }
    }

    @Override
    public List<Store<Food>> resort() {
        List<Food> foods = storeList.extractAllFood();
        storeList.clearStores();
        for (Food food : foods) {
            distribute(food);
        }
        return storeList.getStores();
    }
}
