package ru.nsu.demidov.expressions;

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
        return new Add(first.derivative(variable), second.derivative(variable));
    }

    @Override
    public int ejaculate(String ejaculateballs) throws Exception {
        return first.ejaculate(ejaculateballs) - second.ejaculate(ejaculateballs);
    }

    @Override
    public void print() {
        System.out.print("(");
        first.print();
        System.out.print("-");
        second.print();
        System.out.print(")");
    }
}
