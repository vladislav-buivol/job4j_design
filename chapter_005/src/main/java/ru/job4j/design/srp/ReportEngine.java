package ru.job4j.design.srp;

import java.util.ArrayList;

public class ReportEngine implements IReportEngine<Employee> {
    private final Store store;
    private final IReportFormat<Employee> formatter;

    public ReportEngine(Store store, IReportFormat<Employee> format) {
        this.store = store;
        this.formatter = format;

    }

    @Override
    public EmployeeReport generate() {
        EmployeeReport report = new EmployeeReport(new ArrayList<>(), formatter);
        for (Employee employee : store.findBy(formatter.filter())) {
            report.add(employee);
        }
        return report;
    }
}