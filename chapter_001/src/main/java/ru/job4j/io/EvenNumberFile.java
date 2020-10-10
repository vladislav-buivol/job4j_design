package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        read();
    }

    public static void read() {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                char c = (char) read;
                if (c != '\r') {
                    text.append((char) read);
                }
                if (c == '\n' && text.length() > 1) {
                    printNumber(text);
                }
            }
            printNumber(text);
            System.out.println(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printNumber(StringBuilder text) {
        String value = text.toString().strip();
        if (isNumberEven(text.toString())) {
            System.out.println(value + " is even number");
        } else {
            System.out.println(value + " not even number");
        }
        text.setLength(0);
    }

    private static boolean isNumberEven(String c) {
        try {
            return Double.parseDouble((c).strip()) % 2 == 0;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}