package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Analyze {
    private static final String[] errorCodes = new String[]{"400", "500"};
    private static final String[] okCodes = new String[]{"200", "300"};

    public void unavailable(String source, String target) {
        ArrayList<Entry> result = analyzeLog(source);
        write(target, result);
    }

    public static void main(String[] args) {
        Analyze analyze = new Analyze();
        analyze.unavailable("./server.log", "unavailable.csv");
    }

    private void write(String target, ArrayList<Entry> lines) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            String lineSeparator = System.lineSeparator();
            for (Entry line : lines) {
                out.write(String.format("%s;%s%s", line.startTime(), line.endTime(), lineSeparator));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Entry> analyzeLog(String source) {
        ArrayList<Entry> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            in.lines()
                    .forEach(line -> findTimeRangeWhenServerDoesntWork(result, line));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private void findTimeRangeWhenServerDoesntWork(ArrayList<Entry> result, String line) {
        String time = line.split(" ")[1];
        if (serverDoesntWork(line)) {
            if (result.size() == 0) {
                Entry entry = new Entry();
                entry.startTime = time;
                result.add(entry);
            } else if (result.get(result.size() - 1).endTime != null) {
                Entry entry = new Entry();
                entry.startTime = time;
                result.add(entry);
            }
        } else if (serverWorks(line) && result.size() > 0) {
            Entry entry = result.remove(result.size() - 1);
            entry.endTime = time;
            result.add(entry);
        }
    }

    private boolean serverDoesntWork(String line) {
        String code = line.split(" ")[0];
        return Arrays.asList(errorCodes).contains(code);
    }

    private boolean serverWorks(String line) {
        String code = line.split(" ")[0];
        return Arrays.asList(okCodes).contains(code);
    }

    protected static class Entry {
        private String startTime = null;
        private String endTime = null;

        public Entry(String startTime, String endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public Entry() {
        }

        public String startTime() {
            return startTime;
        }

        public String endTime() {
            return endTime;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "startTime='" + startTime + '\'' +
                    ", endTime='" + endTime + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Entry)) return false;
            Entry entry = (Entry) o;
            return Objects.equals(startTime, entry.startTime) &&
                    Objects.equals(endTime, entry.endTime);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startTime, endTime);
        }
    }
}