package ru.job4j.design.srp;

public interface IReportEngine<T> {
    IReport<T> generate();
}
