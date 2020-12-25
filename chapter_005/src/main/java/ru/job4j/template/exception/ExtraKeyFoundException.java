package ru.job4j.template.exception;

public class ExtraKeyFoundException extends Exception {

    public ExtraKeyFoundException(String message, Throwable cause) {
        super(String.format("Extra key found for: %s", cause));
    }
}
