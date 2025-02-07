package ru.nsu.demidov;

public class Main {
    public static void main(String[] args) {
        Expression sample = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));
        sample.print();
        System.out.println();
        Expression sampleDerivative = sample.derivative("x");
        sampleDerivative.print();
        System.out.println();
        sample = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));
        Expression e = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));
        int result = e.ejaculate("x = 10; y = 13");
        System.out.println(result);
    }
}