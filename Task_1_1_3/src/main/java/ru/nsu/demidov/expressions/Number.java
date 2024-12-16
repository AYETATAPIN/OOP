package ru.nsu.demidov.expressions;


/**
 * Number class.
 */

public class Number extends Expression {
    public double value;

    /**
     * Number constructor.
     */

    public Number(double value) {
        this.value = value;
    }

    /**
     * Derivative method.
     */

    @Override
    public Number derivative(String variable) {
        return new Number(0);
    }

    /**
     * Simplify method.
     */

    @Override
    public Number simplify() {
        return new Number(this.value);
    }

    /**
     * Evaluate method.
     */

    @Override
    public double evaluate(String values) {
        return this.value;
    }

    /**
     * toString method.
     */

    @Override
    public String toString() {
        if (this.value % 1 == 0) {
            return "" + (int) this.value;
        } else {
            return "" + this.value;
        }
    }

    /**
     * Equals method.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Number obj) {
            return value == obj.value;
        }
        return false;
    }
}
