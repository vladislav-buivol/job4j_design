package ru.job4j.io;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AnalyzeTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    private Analyze analyze;
    private ArrayList<Analyze.Entry> expected;
    private File source;
    private File target;
    private final String serverLogData = "200 10:56:01\n" +
            "200 10:57:01\n" +
            "400 10:58:01\n" +
            "200 10:59:01\n" +
            "500 11:01:02\n" +
            "200 11:02:02\n" +
            "200 11:02:02\n" +
            "300 11:02:02\n" +
            "200 11:02:04\n" +
            "500 11:01:05\n" +
            "500 11:01:06\n" +
            "200 11:02:07\n" +
            "400 10:58:08\n" +
            "400 10:58:09\n" +
            "200 11:02:10\n" +
            "500 11:01:11\n" +
            "400 10:58:12\n" +
            "400 10:58:12\n" +
            "400 10:58:12\n" +
            "400 10:58:12\n" +
            "500 11:01:11\n" +
            "500 11:01:11\n" +
            "500 11:01:11\n" +
            "500 11:01:11\n" +
            "200 11:02:12";


    @Before
    public void init() throws IOException {
        source = folder.newFile("server.log");
        target = folder.newFile("unavailable.csv");
        writeData(serverLogData,source);
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
        ArrayList<Analyze.Entry> actual = analyze.analyzeLog(source.getAbsolutePath());
        assertThat(expected, equalTo(actual));
    }

    @Test
    public void fileContentTest() {
        String pathToSource = source.getAbsolutePath();
        String pathToTarget = target.getAbsolutePath();
        analyze.unavailable(pathToSource, pathToTarget);
        System.out.println(pathToSource);
        try (BufferedReader in = new BufferedReader(new FileReader(pathToTarget))) {
            long nrOfLines = Files.lines(Paths.get(pathToTarget)).count();
            assertThat(5L, equalTo(nrOfLines));
            int index = 0;
            String line;
            while ((line = in.readLine()) != null) {
                String[] parts = line.split(";");
                String startTime = parts[0];
                String endTime = parts[1];
                Analyze.Entry currentLine = new Analyze.Entry(startTime, endTime);
                assertThat(expected.get(index++), equalTo(currentLine));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeData(String data, File target){
        try (PrintWriter out = new PrintWriter(target)){
            data = data.replace("\n",System.lineSeparator());
            out.write(data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
