package ru.job4j.lsp;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Создать класс Food сполями.
 * Name, expaireDate, createDate, price, disscount.
 * На основе класса Food создать другие продукты.
 */
public class Food {
    private String name;
    private Calendar expiryDate;
    private Calendar createDate;
    private BigDecimal price;
    private double discount;

    public Food(String name, Calendar expiryDate, Calendar createDate, BigDecimal price, double discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public Calendar getExpiryDate() {
        return expiryDate;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }
}
