package ru.job4j.generics;

import java.util.Iterator;
import java.util.List;

public class Generics {
    public static void main(String[] args) {
        List<?> first = List.of(new Animal(), new Predator(), new Tiger());
        new Generics().printObject(first);

        List<? extends Predator> second = List.of(
                new Tiger(), new Predator(), new Tiger()
        );
        new Generics().printBoundedWildCard(second);

        List<? super Predator> third = List.of(
                new Animal(), new Predator(), new Animal()
        );
        new Generics().printLowerBoundedWildCard(third);
    }

    //1. WildCard.
    public void printObject(List<?> list) {
        for (Iterator<?> it = list.iterator(); it.hasNext(); ) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    //2. Bounded Wildcard
    public void printBoundedWildCard(List<? extends Predator> list) {
        for (Iterator<? extends Predator> it = list.iterator(); it.hasNext(); ) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    //3. Lower bounded wcard
    public void printLowerBoundedWildCard(List<? super Predator> list) {
        for (Iterator<? super Predator> it = list.iterator(); it.hasNext(); ) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }
}