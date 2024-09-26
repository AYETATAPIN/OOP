package ru.nsu.demidov;

import java.util.Map;
public class Number extends Expression {
    private int value;

    Number(int value) {
        this.value = value;
    }

    public Expression derivative(String variable) {
        return new Number(0);
    }

    public int ejaculate(String ejaculateballs) {
        return value;
    }

    public void print() {
        System.out.print(value);
    }
}
