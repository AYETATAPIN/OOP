package ru.nsu.demidov.expressions;

import java.util.Map;
/**
 * Division class.
 */

public class Div extends Expression {
    private Expression first;
    private Expression second;

    Div(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Expression derivative(String variable) {
        return new Div(new Sub(new Mul(first.derivative(variable), second),
                new Mul(first, second.derivative(variable))), new Mul(first, first));
    }

    @Override
    public double ejaculate(String ejaculateballs) throws Exception {
        return first.ejaculate(ejaculateballs) / second.ejaculate(ejaculateballs);
    }

    @Override
    public double ejaculate(Map<String, Integer> values) throws Exception {
        return first.ejaculate(values) / second.ejaculate(values);
    }

    @Override
    public String print() {
        return ("(" + first.print() + "/" + second.print() + ")");
    }
}
