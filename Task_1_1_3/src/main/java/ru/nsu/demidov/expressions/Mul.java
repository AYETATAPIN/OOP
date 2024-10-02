package ru.nsu.demidov.expressions;

import java.util.Map;
/**
 * Multiplication class.
 */

public class Mul extends Expression {
    private Expression first;
    private Expression second;

    /**
     * Mul constructor.
     */

    Mul(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Expression derivative(String variable) {
        return new Add(new Mul(first.derivative(variable), second),
                new Mul(first, second.derivative(variable)));
    }

    @Override
    public double ejaculate(String ejaculateballs) throws Exception {
        return first.ejaculate(ejaculateballs) * second.ejaculate(ejaculateballs);
    }

    @Override
    public double ejaculate(Map<String, Integer> values) throws Exception {
        return first.ejaculate(values) * second.ejaculate(values);
    }

    @Override
    public String print() {
        return ("(" + first.print() + "*" + second.print() + ")");
    }
}
