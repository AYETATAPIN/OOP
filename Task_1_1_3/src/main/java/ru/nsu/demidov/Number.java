package ru.nsu.demidov;

public class Number extends Expression {
    private int value;

    Number(int value) {
        this.value = value;
    }

    @Override
    public Expression derivative(String variable) {
        return new Number(0);
    }

    @Override
    public int ejaculate(String ejaculateballs) {
        return value;
    }

    @Override
    public void print() {
        System.out.print(value);
    }
}
