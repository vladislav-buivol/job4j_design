package ru.job4j.ood.isp;

class Computer implements Device {

    private String buffer;

    @Override
    public void in(String data) {
        this.buffer = data;
    }

    @Override
    public void calculate() {
        this.buffer = "Calc by computer: " + buffer;
    }

    @Override
    public void output() {
        System.out.println(buffer);
    }

}