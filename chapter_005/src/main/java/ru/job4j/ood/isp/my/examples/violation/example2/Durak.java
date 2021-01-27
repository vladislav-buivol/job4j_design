package ru.job4j.ood.isp.my.examples.violation.example2;

public class Durak implements CardGame {
    @Override
    public void putCard() {

    }

    @Override
    public void beat() {

    }

    @Override
    public void takeCards() {

    }

    @Override
    public void hit() {

    }

    @Override
    public void stand() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void insurance() {
        throw new UnsupportedOperationException();
    }
}
