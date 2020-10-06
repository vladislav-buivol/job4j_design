package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> node = findBy(parent);
        if (node.isPresent() && findBy(child).isEmpty()) {
            node.get().children.add(new Node<E>(child));
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return getNode(el -> el.value.equals(value));
    }

    public boolean isBinary() {
        return getNode(node -> node.children.size() > 2).isEmpty();
    }

    public boolean isNodeBinary(Node<E> node) {
        if (node == null || node.children.size() == 0) {
            return true;
        }
        if (node.children.size() > 2) {
            return false;
        }
        Queue<Node<E>> data = new LinkedList<>(node.children);
        return isNodeBinary(data.poll()) && isNodeBinary(data.poll());
    }

    private Optional<Node<E>> getNode(Predicate<Node<E>> predicate) {
        Queue<Node<E>> data = new LinkedList<>();
        Optional<Node<E>> rsl = Optional.empty();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (predicate.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "root=" + root +
                '}';
    }
}