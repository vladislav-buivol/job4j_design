package ru.job4j.ood.isp.menu.menu;

import ru.job4j.ood.isp.menu.utils.Generator;

public class DummyGenerator implements Generator<String> {
    private final int dummy;

    public DummyGenerator(int dummy) {
        this.dummy = dummy;
    }

    @Override
    public String generate() {
        return String.valueOf(dummy);
    }
}
