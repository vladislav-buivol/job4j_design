package ru.job4j.lsp;

import java.math.BigDecimal;
import java.util.Calendar;

public class Cheese extends Food {
    public Cheese(String name, Calendar expiryDate, Calendar createDate, BigDecimal price, double discount) {
        super(name, createDate, expiryDate, price, discount);
    }
}
