package ru.nsu.demidov.expressions;

/**
 * Expressions input.
 */

public class Main {

    /**
     * Expressions input.
     */

    public static void main(String[] args) throws Exception {
        Expression sample = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));
        System.out.println(sample.print());
        Expression sampleDerivative = sample.derivative("x");
        System.out.println(sampleDerivative.print());
        sample = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));
        double result = sample.ejaculate("x = 10; y = 13");
        System.out.println(result);
    }
}