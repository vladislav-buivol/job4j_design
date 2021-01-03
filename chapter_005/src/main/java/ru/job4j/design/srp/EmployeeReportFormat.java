package ru.job4j.design.srp;

import ru.job4j.design.srp.fields.EmployeeFields;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;


public class EmployeeReportFormat implements ReportFormat<Employee> {
    private final Predicate<Employee> predicate;
    private final String format;
    private final EmployeeFields.Fields[] headers;
    private String header;

    /**
     * @param predicate - filter to find required employees
     * @param format    - Strign format. E.g %s;%s;%s
     */
    public EmployeeReportFormat(Predicate<Employee> predicate, String format, EmployeeFields.Fields... headers) {
        if (!format.contains("%s")) {
            throw new RuntimeException("Incorrect format. Format must contains '%s'");
        }
        this.header = String.format(format, headers);
        this.format = format;
        this.predicate = predicate;
        this.headers = headers;
    }

    @Override
    public Predicate<Employee> filter() {
        return predicate;
    }

    @Override
    public String format(Employee data) {
        String formattedData = String.format(format, headers);
        List<EmployeeFields.Fields> requiredData = Arrays.asList(headers);
        formattedData = format(data, formattedData, requiredData);
        return formattedData;
    }

    public String getFormattedHeader() {
        return header;
    }

    private String format(Employee data, String formattedData, List<EmployeeFields.Fields> requiredData) {
        for (EmployeeFields.Fields header : headers) {
            if (!requiredData.contains(header)) {
                continue;
            }
            switch (header) {
                case Name -> formattedData = formattedData.replace(header.toString(), data.getName());
                case Hired -> formattedData = formattedData.replace(header.toString(), data.getHired().toString());
                case Fired -> formattedData = formattedData.replace(header.toString(), data.getFired().toString());
                case Salary -> formattedData = formattedData.replace(header.toString(), String.valueOf(data.getSalary()));
                default -> throw new RuntimeException(String.format("Header '%s' not found", header));
            }
        }
        return formattedData;
    }

    public EmployeeFields.Fields[] getHeaders() {
        return headers;
    }

    public String getFormat() {
        return format;
    }
}
