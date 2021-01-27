package ru.job4j.ood.isp.my.examples.violation.example1;

/**
 * Controller for Remote-Controlled Car
 */
public class RemoteControllerTransmitter implements Controller {
    @Override
    public void turnOn() {

    }

    @Override
    public void turnOff() {

    }

    @Override
    public void switchChannel() {

    }

    @Override
    public void increaseVolume() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void decreaseVolume() {
        throw new UnsupportedOperationException();
    }
}
