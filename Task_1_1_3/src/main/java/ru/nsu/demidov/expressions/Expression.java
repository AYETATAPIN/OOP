package ru.nsu.demidov.expressions;

/**
 * Abstract class expression.
 */

public abstract class Expression {

    /**
     * Derivative method.
     */

    public abstract Expression derivative(String variable);

    /**
     * Simplify method.
     */

    public abstract Expression simplify();


    /**
     * Evaluate method.
     */

    public abstract double evaluate(String values);

    /**
     * Print method.
     */

    public void print() {
        System.out.println(this.toString());
    }
}
