package ru.job4j.design.srp;

import java.util.function.Predicate;

public interface ReportFormat<T> {
    /**
     * filter to find required data.
     *
     * @return filter
     */
    Predicate<T> filter();

    /**
     * @return formatted data
     */
    String format(T data);
}
