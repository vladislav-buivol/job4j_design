package ru.job4j.design.srp;

import org.json.JSONObject;
import ru.job4j.design.srp.fields.EmployeeFields;

public class JsonConverter implements Converter<Employee> {
    private final ReportFormat<Employee> format;

    public JsonConverter(ReportFormat<Employee> format) {
        this.format = format;
    }

    @Override
    public String convert(Report<Employee> report) {
        StringBuilder sb = new StringBuilder();
        EmployeeFields.Fields[] headers = ((EmployeeReportFormat) format).getHeaders();
        String separator = ((EmployeeReportFormat) format).getSeparator();
        sb.append("{")
                .append(System.lineSeparator());
        for (int i = 1; i < report.getReport().size(); i++) {
            JSONObject jsonObject = new JSONObject();
            String row = ((EmployeeReport) report).getReport().get(i);
            String[] employeeData = row.split(separator);
            for (int j = 0; j < employeeData.length; j++) {
                jsonObject.put(headers[j].toString(), employeeData[j]);
            }
            sb.append(jsonObject)
                    .append(System.lineSeparator());
            if (i + 1 < report.getReport().size()) {
                sb.append(",");
            }

        }
        sb.append("}");
        return sb.toString();
    }
}
