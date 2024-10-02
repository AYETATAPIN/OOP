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
    public double ejaculate(Map<String, Integer> values) throws Exception {
        return values.get(value);
    }

    @Override
    public String print() {
        return value;
    }
}
