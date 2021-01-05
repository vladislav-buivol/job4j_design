package ru.job4j.ood.ocp;

import java.util.function.BiFunction;

public class CalculatorExample {

    private static class SimpleCalculator {
        public int sum(int a, int b) {
            return a + b;
        }
    }

    private static class AbstractCalculator<T> {
        public T calculate(BiFunction<T, T, T> function, T first, T second) {
            return function.apply(first, second);
        }
    }

}