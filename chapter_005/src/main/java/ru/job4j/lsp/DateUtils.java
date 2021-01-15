package ru.job4j.lsp;

import java.time.LocalDateTime;
import java.util.Calendar;

import static java.time.temporal.ChronoUnit.DAYS;

public class DateUtils {

    public static LocalDateTime toLocalDate(Calendar calendar) {
        return LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId()).toLocalDate().atStartOfDay();
    }

    public static long daysBetween(Calendar startDate, Calendar endDate) {
        return DAYS.between(toLocalDate(startDate), toLocalDate(endDate));
    }

    public static long remainingDays(Calendar endDate) {
        Calendar now = Calendar.getInstance();
        return daysBetween(now, endDate);
    }

    public static <T extends Food> double usedByPerCent(T food) {
        Calendar now = Calendar.getInstance();
        long totalDays = Math.abs(daysBetween(food.getCreateDate(), food.getExpiryDate()));
        long leftDays = daysBetween(now, food.getExpiryDate());
        if (totalDays - leftDays < 0) {
            return 1;
        }
        return 1 - (double) leftDays / totalDays;
    }
}
