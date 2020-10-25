package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

/**
 * An example of possible encoding problems
 */
public class UsageEncoding {

    public static void main(String[] args) {
        String path = "chapter_001/data/text.txt";
        UsageEncoding encoding = new UsageEncoding();
        List<String> strings = List.of(
                "Новая строка 1",
                "Новая строка 2",
                "Новая строка 3",
                "Новая строка 4",
                "Новая строка 5"
        );
        for (String str : strings) {
            encoding.writeDataInFile(path, str);
        }
        String s = encoding.readFile(path);
        System.out.println("Данные из файла: ");
        System.out.println(s);
    }
    public String readFile(String path) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            int data;
            while ((data = br.read()) > 0) {
                builder.append((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public void writeDataInFile(String path, String data) {
        try (BufferedWriter br = new BufferedWriter(
                new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            br.write(data + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}