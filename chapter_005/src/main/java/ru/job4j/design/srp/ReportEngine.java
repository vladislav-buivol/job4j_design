package ru.job4j.design.srp;

public interface ReportEngine<T> {
    Report<T> generate();
}
