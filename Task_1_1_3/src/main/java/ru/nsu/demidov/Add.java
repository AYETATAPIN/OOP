package ru.nsu.demidov;

public class Add extends Expression {
    private Expression first;
    private Expression second;

    Add(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Expression derivative(String variable) {
        return new Add(first.derivative(variable), second.derivative(variable));
    }

    @Override
    public int ejaculate(String ejaculateballs) {
        return first.ejaculate(ejaculateballs) + second.ejaculate(ejaculateballs);
    }

    @Override
    public void print() {
        System.out.print("(");
        first.print();
        System.out.print("+");
        second.print();
        System.out.print(")");
    }
}
