package ru.job4j.ood.isp.menu.utils;

import java.util.UUID;

public class HeaderIdGenerator implements Generator<String> {

    @Override
    public String generate() {
        return UUID.randomUUID().toString().substring(0, 4);
    }
}
