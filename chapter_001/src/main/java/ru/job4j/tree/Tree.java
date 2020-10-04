package ru.job4j.tree;

import java.util.*;

class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        if (findBy(parent).isPresent() && findBy(child).isEmpty()) {
            findBy(parent).get().children.add(new Node<E>(child));
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    public boolean isBinary() {
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> node = data.poll();
            data.addAll(node.children);
            if (node.children.size() > 2) {
                return false;
            }
        }
        return true;
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


    @Override
    public String toString() {
        return "Tree{" +
                "root=" + root +
                '}';
    }

}