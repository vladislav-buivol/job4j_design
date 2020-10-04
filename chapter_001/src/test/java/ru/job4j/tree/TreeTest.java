package ru.job4j.tree;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class TreeTest {
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void isBinaryTree() {
        Tree<Integer> binary = new Tree<>(1);
        Tree<Integer> binary2 = new Tree<>(1);
        Tree<Integer> notBinary = new Tree<>(1);
        Tree<Integer> notBinary2 = new Tree<>(1);
        binary.add(1, 2);
        binary.add(1, 3);
        notBinary.add(1, 2);
        notBinary.add(1, 3);
        notBinary.add(1, 4);
        binary2.add(1,2);
        binary2.add(1,3);
        binary2.add(2,4);
        binary2.add(2,5);
        binary2.add(4,6);
        binary2.add(4,7);
        binary2.add(7,8);
        binary2.add(7,9);
        binary2.add(3,10);
        binary2.add(3,11);
        binary2.add(11,12);
        binary2.add(11,13);
        notBinary2.add(1,2);
        notBinary2.add(1,3);
        notBinary2.add(2,4);
        notBinary2.add(2,5);
        notBinary2.add(4,6);
        notBinary2.add(4,7);
        notBinary2.add(4,8);

        assertThat(binary.isBinary(),is(true));
        assertThat(binary2.isBinary(),is(true));
        assertThat(notBinary.isBinary(),is(false));
        assertThat(notBinary2.isBinary(),is(false));
    }

}
