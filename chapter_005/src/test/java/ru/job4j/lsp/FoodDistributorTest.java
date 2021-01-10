package ru.job4j.lsp;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Calendar;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class FoodDistributorTest {
    Warehouse warehouse;
    Shop shop;
    Trash trash;
    Strategy<Food> strategy;

    @Before
    public void init() {
        warehouse = new Warehouse();
        shop = new Shop();
        trash = new Trash();
        strategy = new ControlQuality(warehouse, shop, trash);
    }

    @Test
    public void sentToWarehouse() {
        Calendar expiryDate = Calendar.getInstance();
        Calendar createDate = Calendar.getInstance();
        expiryDate.add(Calendar.DATE, 10);
        Cheese cheese = new Cheese("Cheese", createDate, expiryDate, new BigDecimal("12"), 0);
        strategy.doOperation(cheese);
        assertThat(warehouse.size(), equalTo(1));
        assertThat(shop.size(), equalTo(0));
        assertThat(trash.size(), equalTo(0));
        assertThat(cheese.getDiscount(), equalTo(0d));
    }

    @Test
    public void sentToStore() {
        Calendar expiryDate = Calendar.getInstance();
        Calendar createDate = Calendar.getInstance();
        expiryDate.add(Calendar.DATE, 10);
        createDate.add(Calendar.DATE, -7);
        Cheese cheese = new Cheese("Cheese", createDate, expiryDate, new BigDecimal("12"), 0);
        strategy.doOperation(cheese);
        assertThat(warehouse.size(), equalTo(0));
        assertThat(shop.size(), equalTo(1));
        assertThat(trash.size(), equalTo(0));
        assertThat(cheese.getDiscount(), equalTo(0d));
    }

    @Test
    public void discount() {
        Calendar expiryDate = Calendar.getInstance();
        Calendar createDate = Calendar.getInstance();
        expiryDate.add(Calendar.DATE, 10);
        createDate.add(Calendar.DATE, -30);
        Cheese cheese = new Cheese("Cheese", createDate, expiryDate, new BigDecimal("12"), 0);
        strategy.doOperation(cheese);
        assertThat(warehouse.size(), equalTo(0));
        assertThat(shop.size(), equalTo(1));
        assertThat(trash.size(), equalTo(0));
        assertThat(true, equalTo(cheese.getDiscount() > 0));
    }

    @Test
    public void sentToTrash() {
        Calendar expiryDate = Calendar.getInstance();
        Calendar createDate = Calendar.getInstance();
        expiryDate.add(Calendar.DATE, -1);
        Cheese cheese = new Cheese("Cheese", createDate, expiryDate, new BigDecimal("12"), 0);
        strategy.doOperation(cheese);
        assertThat(warehouse.size(), equalTo(0));
        assertThat(shop.size(), equalTo(0));
        assertThat(trash.size(), equalTo(1));
        assertThat(cheese.getDiscount(), equalTo(0d));
    }


}