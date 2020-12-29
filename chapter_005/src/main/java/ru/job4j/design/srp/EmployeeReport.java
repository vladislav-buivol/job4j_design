package ru.job4j.design.srp;

import ru.job4j.design.srp.fields.EmployeeFields;

import java.util.*;

public class EmployeeReport implements IReport<Employee> {
    private final IReportFormat<Employee> formatter;
    private final ArrayList<Employee> data;
    private final EmployeeFields.Fields[] headers;

    public EmployeeReport(ArrayList<Employee> data, IReportFormat<Employee> format) {
        this.data = data;
        this.formatter = format;
        this.headers = ((EmployeeReportFormat) format).getHeaders();
    }

    @Override
    public void add(Employee employee) {
        data.add(employee);
    }

    @Override
    public Employee delete(int i) {
        return data.remove(i);
    }

    @Override
    public List<String> getReport() {
        ArrayList<String> report = new ArrayList<>();
        report.add(((EmployeeReportFormat) formatter).getFormattedHeader());
        for (Employee employee : data) {
            report.add(formatter.format(employee));
        }
        return Collections.unmodifiableList(report);
    }

    @Override
    public Collection<Employee> sort(Comparator<Employee> comparator) {
        data.sort(comparator);
        return Collections.unmodifiableList(data);
    }

}
