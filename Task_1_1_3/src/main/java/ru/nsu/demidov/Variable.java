package ru.nsu.demidov;

import java.util.Objects;

public class Variable extends Expression {
    private String value;

    Variable(String value) {
        this.value = value;
    }

    public Expression derivative(String variable) {
        if (Objects.equals(this.value, variable) == true) {
            return new Number(1);
        }
        return new Number(0);
    }

    public int ejaculate(String ejaculateballs) {
        ballsParser(ejaculateballs);
        return this.values.get(this.value);
    }

    public void print() {
        System.out.print(value);
    }
}
