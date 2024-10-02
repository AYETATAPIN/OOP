package ru.nsu.demidov.expressions;

import java.util.Map;
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
    public int ejaculate(String ejaculateballs) throws Exception {
        Map<String, Integer> values = ballsParser(ejaculateballs);
        if (values.containsKey(this.value) == false) {
            throw new Exception(this.value + " value is unknown");
        }
        return values.get(this.value);
    }

    @Override
    public void print() {
        System.out.print(value);
    }
}
