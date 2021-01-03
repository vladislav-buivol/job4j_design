package ru.job4j.design.srp;

public class HtmlConverter implements Converter<Employee> {
    private final String format;

    public HtmlConverter(String format) {
        this.format = format;
    }

    @Override
    public String convert(Report<Employee> report) {
        StringBuilder sb = new StringBuilder();
        for (String row : report.getReport()) {
            sb.append(String.format(format, row, System.lineSeparator()));
        }
        return sb.toString();
    }
}
