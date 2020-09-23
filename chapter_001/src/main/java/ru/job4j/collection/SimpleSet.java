package ru.job4j.collection;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {
    private SimpleArray<E> container;
    private int containerInitSize = 10;
    private int modCount = 0;

    public SimpleSet() {
        this.container = new SimpleArray<E>();
    }

    public void add(E e) {
        if (contains(e)) {
            return;
        }
        container.add(e);
    }

    @Override
    public Iterator<E> iterator() {
        return container.iterator();
    }

    private boolean contains(E e) {
        for (int i = 0; i < container.size(); i++) {
            if (e.equals(container.get(i))) {
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        return container.size();
    }
}
