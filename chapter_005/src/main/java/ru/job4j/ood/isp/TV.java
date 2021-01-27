package ru.job4j.ood.isp;

class TV implements Device {

    private String command;

    @Override
    public void in(String data) {
        this.command = command;
    }

    @Override
    public void calculate() {
        System.out.println("Execute: " + command);
    }

    @Override
    public void output() {
        System.out.println("Show TV program");
    }
}