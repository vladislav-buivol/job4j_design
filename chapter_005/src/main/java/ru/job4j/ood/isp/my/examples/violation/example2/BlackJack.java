package ru.job4j.ood.isp.my.examples.violation.example2;

public class BlackJack implements CardGame {
    @Override
    public void putCard() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void beat() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void takeCards() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void hit() {

    }

    @Override
    public void stand() {

    }

    @Override
    public void insurance() {

    }
}
