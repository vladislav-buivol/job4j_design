package ru.job4j.design.srp;

public interface Converter<T> {
    String convert(Report<T> dataToConvert);
}
