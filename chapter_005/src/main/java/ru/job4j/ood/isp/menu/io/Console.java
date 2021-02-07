package ru.job4j.ood.isp.menu.io;

import java.util.Scanner;

public class Console implements Input, Output<String> {
    private final Scanner in;

    public Console(Scanner in) {
        this.in = in;
    }

    @Override
    public String read()  {
        return in.nextLine();
    }

    @Override
    public void out(String o) {
        System.out.println(o);
    }
}
