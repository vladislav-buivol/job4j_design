package ru.job4j.design.srp;

public class XmlConverter implements Converter<Employee> {

    private final String format;

    public XmlConverter(String format) {
        this.format = format;
    }

    @Override
    public String convert(Report<Employee> report) {
        StringBuilder sb = new StringBuilder();
        sb.append("<EmployeeList>");
        createHader((EmployeeReport) report, sb);
        for (int i = 1; i < report.getReport().size(); i++) {
            String row = ((EmployeeReport) report).getReport().get(i);
            createRow(sb, row);
        }
        sb.append("</EmployeeList>");
        return sb.toString();
    }

    private void createRow(StringBuilder sb, String row) {
        sb.append("<employee>");
        sb.append(row);
        sb.append("</employee>");
        sb.append(System.lineSeparator());
    }

    private void createHader(EmployeeReport report, StringBuilder sb) {
        sb.append("<header>");
        sb.append(report.getReport().get(0));
        sb.append("</header>");
        sb.append(System.lineSeparator());
    }
}
