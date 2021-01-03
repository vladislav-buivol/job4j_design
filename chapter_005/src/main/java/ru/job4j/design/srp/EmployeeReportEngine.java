package ru.job4j.design.srp;

import java.util.ArrayList;

public class EmployeeReportEngine implements ReportEngine<Employee> {
    private final Store store;
    private final ReportFormat<Employee> formatter;

    public EmployeeReportEngine(Store store, ReportFormat<Employee> format) {
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