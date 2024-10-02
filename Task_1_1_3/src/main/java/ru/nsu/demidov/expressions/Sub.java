package ru.nsu.demidov.expressions;

import java.util.Map;
/**
 * Subtraction class.
 */


public class Sub extends Expression {
    private Expression first;
    private Expression second;

    /**
     * Sub constructor.
     */

    Sub(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Expression derivative(String variable) {
        return new Sub(first.derivative(variable), second.derivative(variable));
    }

    @Override
    public double ejaculate(String ejaculateballs) throws Exception {
        return first.ejaculate(ejaculateballs) - second.ejaculate(ejaculateballs);
    }

    @Override
    public double ejaculate(Map<String, Integer> values) throws Exception {
        return first.ejaculate(values) - second.ejaculate(values);
    }

    @Override
    public String print() {
        return ("(" + first.print() + "-" + second.print() + ")");
    }
}
