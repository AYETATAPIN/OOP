package ru.nsu.demidov;

import java.util.Map;
public class Div extends Expression {
    private Expression first;
    private Expression second;

    Div(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    public Expression derivative(String variable) {
        return new Div(new Sub(new Mul(first.derivative(variable), second),
                new Mul(first, second.derivative(variable))), new Mul(first, first));
    }

    public int ejaculate(String ejaculateballs) {
        return first.ejaculate(ejaculateballs) / second.ejaculate(ejaculateballs);
    }

    public void print() {
        System.out.print("(");
        first.print();
        System.out.print("/");
        second.print();
        System.out.print(")");
    }
}
