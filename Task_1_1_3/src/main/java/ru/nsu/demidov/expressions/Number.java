package ru.nsu.demidov.expressions;

import java.util.Map;
import java.util.Objects;
/**
 * Number class.
 */


public class Number extends Expression {
    private double value;

    /**
     * Number constructor.
     */

    Number(int value) {
        this.value = value;
    }

    @Override
    public Expression derivative(String variable) {
        return new Number(0);
    }

    @Override
    public double ejaculate(String ejaculateballs) {
        return value;
    }

    @Override
    public double ejaculate(Map<String, Integer> values) throws Exception {
        return value;
    }

    @Override
    public String print() {
        if (value == (int) value) {
            return ("" + ((int) value));
        }
        return "" + value;
    }
}
