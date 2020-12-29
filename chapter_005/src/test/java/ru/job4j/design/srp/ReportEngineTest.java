package ru.job4j.design.srp;

import org.junit.Test;

import java.util.Calendar;
import java.util.Comparator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ru.job4j.design.srp.fields.EmployeeFields.Fields.*;

public class ReportEngineTest {

    @Test
    public void whenGeneratedAllFields() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        EmployeeReportFormat format = new EmployeeReportFormat(em -> true, "%s;%s;%s;%s;", Name, Hired, Fired, Salary);
        Employee worker = new Employee("Ivan", now, now, 100);
        HtmlConverter converter = new HtmlConverter("%s%s");
        store.add(worker);
        ReportEngine engine = new ReportEngine(store, format);
        StringBuilder expect = new StringBuilder()
                .append("Name;Hired;Fired;Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(converter.convert(engine.generate()), is(expect.toString()));
    }


    @Test
    public void whenGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        HtmlConverter converter = new HtmlConverter("%s%s");
        EmployeeReportFormat format = new EmployeeReportFormat(em -> true, "%s;%s;", Name, Salary);
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportEngine engine = new ReportEngine(store, format);
        StringBuilder expect = new StringBuilder();
        expect.append("Name;Salary;");
        expect.append(System.lineSeparator());
        addWorkerToStr(worker, expect);
        EmployeeReport report = engine.generate();
        assertThat(converter.convert(report), is(expect.toString()));
    }


    @Test
    public void whenGeneratedAndSort() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        HtmlConverter converter = new HtmlConverter("%s%s");
        EmployeeReportFormat format = new EmployeeReportFormat(em -> true, "%s;%s;", Name, Salary);
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Misha", now, now, 200);
        Employee worker3 = new Employee("Petja", now, now, 300);
        store.add(worker);
        store.add(worker2);
        store.add(worker3);
        ReportEngine engine = new ReportEngine(store, format);
        StringBuilder expect = new StringBuilder();
        expect.append("Name;Salary;");
        expect.append(System.lineSeparator());
        addWorkerToStr(worker, expect);
        addWorkerToStr(worker2, expect);
        addWorkerToStr(worker3, expect);

        StringBuilder expectSorted = new StringBuilder();
        expectSorted.append("Name;Salary;");
        expectSorted.append(System.lineSeparator());
        addWorkerToStr(worker3, expectSorted);
        addWorkerToStr(worker2, expectSorted);
        addWorkerToStr(worker, expectSorted);
        EmployeeReport report = engine.generate();
        assertThat(converter.convert(report), is(expect.toString()));
        report.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                if (o1.getSalary() > o2.getSalary()) {
                    return -1;
                } else if (o1.getSalary() < o2.getSalary()) {
                    return 1;
                }
                return 0;
            }
        });
        assertThat(converter.convert(report), is(expectSorted.toString()));
    }

    private void addWorkerToStr(Employee worker, StringBuilder expect) {
        expect.append(worker.getName()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
    }
}