package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.lines().
                    filter(line -> containsStatusCode(line, "404"))
                    .forEach(lines::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
    }


    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            log.stream()
                    .map(LogFilter::addNewLineSymbol)
                    .forEach(out::write);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String addNewLineSymbol(String line) {
        return line + System.lineSeparator();
    }

    private static boolean containsStatusCode(String line, String statusCode) {
        return line.split("\"")[2].strip().startsWith(statusCode);
    }
}