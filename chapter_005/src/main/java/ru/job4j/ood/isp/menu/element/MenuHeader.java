package ru.job4j.ood.isp.menu.element;

import ru.job4j.ood.isp.menu.utils.Generator;

import java.util.ArrayList;
import java.util.List;

public class MenuHeader implements Header {
    private final String name;
    private List<Header> children = new ArrayList<>();
    private Header parent = null;
    private final String id;

    public MenuHeader(String name, Generator<String> generator) {
        this.name = name;
        this.id = generator.generate();
    }

    public MenuHeader(String name, List<Header> children, Generator<String> generator) {
        this.name = name;
        this.id = generator.generate();
        this.children = children;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public void add(Header header) {
        children.add(header);
    }

    @Override
    public Header getById(String id) {
        for (Header header : children) {
            if (header.getID().equals(id)) {
                return header;
            } else {
                Header res = header.getById(id);
                if (res != null) {
                    return header.getById(id);
                }
            }
        }
        return null;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public List<Header> getAll() {
        return children;
    }

    @Override
    public String toString() {
        return name;
    }
}
