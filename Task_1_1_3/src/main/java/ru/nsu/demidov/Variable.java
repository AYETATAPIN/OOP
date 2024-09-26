package ru.nsu.demidov;

import java.util.Objects;

/**
 * Variable class.
 */


public class Variable extends Expression {
    private String value;

    /**
     * Variable constructor.
     */

    Variable(String value) {
        this.value = value;
    }

    @Override
    public Expression derivative(String variable) {
        if (Objects.equals(this.value, variable) == true) {
            return new Number(1);
        }
        return new Number(0);
    }

    @Override
    public int ejaculate(String ejaculateballs) {
        ballsParser(ejaculateballs);
        return this.values.get(this.value);
    }

    @Override
    public void print() {
        System.out.print(value);
    }
}
