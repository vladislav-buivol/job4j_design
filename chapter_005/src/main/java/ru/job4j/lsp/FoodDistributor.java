package ru.job4j.lsp;

import java.math.BigDecimal;
import java.util.Calendar;

public class FoodDistributor {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        Distributor<Food> distributor = new ControlQuality(warehouse, shop, trash);
        Calendar expiryDate = Calendar.getInstance();
        Calendar createDate = Calendar.getInstance();
        expiryDate.set(2021, Calendar.JANUARY, 11);
        createDate.set(2021, Calendar.JANUARY, 1);
        Cheese cheese = new Cheese("Cheese", createDate, expiryDate, new BigDecimal("12"), 0);
        distributor.distribute(cheese);
    }
}
