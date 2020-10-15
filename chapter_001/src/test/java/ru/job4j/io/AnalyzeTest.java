package ru.job4j.io;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class AnalyzeTest {
    Analyze analyze;
    ArrayList<Analyze.Entry> expected;
    String source = "./data/testData/server.log";
    String target = "./data/testData/unavailable.csv";

    @Before
    public void init() {
        analyze = new Analyze();
        expected = new ArrayList<>();
        expected.add(new Analyze.Entry("10:58:01", "10:59:01"));
        expected.add(new Analyze.Entry("11:01:02", "11:02:04"));
        expected.add(new Analyze.Entry("11:01:05", "11:02:07"));
        expected.add(new Analyze.Entry("10:58:08", "11:02:10"));
        expected.add(new Analyze.Entry("11:01:11", "11:02:12"));
    }

    @Test
    public void analyzeLogTest() {
        ArrayList<Analyze.Entry> actual = analyze.analyzeLog("./data/testData/server.log");
        assertThat(expected, equalTo(actual));
    }

    @Test
    public void fileContentTest() {
        analyze.unavailable(source, target);
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            long nrOfLines = Files.lines(Paths.get(target)).count();
            assertThat(5L, equalTo(nrOfLines));
            int index = 0;
            String line;
            while ((line = in.readLine()) != null){
                String[] parts = line.split(";");
                String startTime = parts[0];
                String endTime = parts[1];
                Analyze.Entry currentLine = new Analyze.Entry(startTime,endTime);
                assertThat(expected.get(index++),equalTo(currentLine));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
