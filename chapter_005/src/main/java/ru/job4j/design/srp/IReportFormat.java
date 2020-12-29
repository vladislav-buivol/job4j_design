package ru.job4j.design.srp;

import java.util.function.Predicate;

public interface IReportFormat<T> {
    /**
     * filter to find required data.
     *
     * @return filter
     */
    Predicate<T> filter();

    /**
     * @param data
     * @return formatted data
     */
    String format(T data);
}
