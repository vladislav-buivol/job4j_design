package ru.job4j.template.exception;

public class KeyNotFoundException extends Exception {

    public KeyNotFoundException(String message, Throwable cause) {
        super(String.format("Key not found for: %s", cause));
    }
}
