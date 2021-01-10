package ru.job4j.lsp;


import java.time.LocalDateTime;
import java.util.Calendar;

import static java.time.temporal.ChronoUnit.DAYS;

public class ControlQuality implements Strategy<Food> {
    private final Warehouse warehouse;
    private final Shop shop;
    private final Trash trash;

    public ControlQuality(Warehouse warehouse, Shop shop, Trash trash) {
        this.warehouse = warehouse;
        this.shop = shop;
        this.trash = trash;
    }

    @Override
    public void doOperation(Food food) {
        Calendar now = Calendar.getInstance();
        long totalDays = daysBetween(food.getCreateDate(), food.getExpiryDate());
        long remainingDays = daysBetween(now, food.getExpiryDate());

        double spent = 1 - (double) remainingDays / totalDays;
        if (spent < 0.25 && remainingDays > 0) {
            warehouse.add(food);
        } else if (spent < 0.75 && remainingDays > 0) {
            shop.add(food);
        } else if (spent >= 0.75 && remainingDays > 0) {
            shop.add(food);
            food.setDiscount(0.5);
        } else {
            trash.add(food);
        }

    }

    private LocalDateTime toLocalDate(Calendar calendar) {
        return LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId()).toLocalDate().atStartOfDay();
    }

    private long daysBetween(Calendar startDate, Calendar endDate) {
        return DAYS.between(toLocalDate(startDate), toLocalDate(endDate));
    }
}
