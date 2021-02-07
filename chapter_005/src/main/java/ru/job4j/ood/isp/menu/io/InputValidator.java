package ru.job4j.ood.isp.menu.io;

public class InputValidator implements Validator {
    @Override
    public boolean validate(String input) {
        try {
            int i = Integer.parseInt(input);
            if (i > -1) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
