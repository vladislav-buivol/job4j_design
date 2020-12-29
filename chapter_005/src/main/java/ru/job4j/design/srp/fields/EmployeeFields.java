package ru.job4j.design.srp.fields;

public class EmployeeFields {
    public enum Fields {
        Name("Name"),
        Hired("Hired"),
        Fired("Fired"),
        Salary("Salary");

        String value;

        Fields(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

}