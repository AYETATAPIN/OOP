package ru.nsu.demidov.expressions;

/**
 * Add class.
 */

public class Add extends Expression {
    private Expression left;
    private Expression right;

    /**
     * Add constructor.
     */

    public Add(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Derivative method.
     */

    @Override
    public Expression derivative(String variable) {
        return new Add(this.left.derivative(variable), this.right.derivative(variable));
    }

    /**
     * Simplify method.
     */

    @Override
    public Expression simplify() {
        Add simplifiedSum = new Add(this.left.simplify(), this.right.simplify());
        if (simplifiedSum.left instanceof Number leftNumber
                && simplifiedSum.right instanceof Number rightNumber) {
            return new Number(leftNumber.getValue() + rightNumber.getValue());
        } else {
            return simplifiedSum;
        }
    }

    /**
     * Evaluate method.
     */

    @Override
    public double evaluate(String values) {
        return this.left.evaluate(values) + this.right.evaluate(values);
    }

    /**
     * toString method.
     */

    @Override
    public String toString() {
        return "(" + this.left.toString() + "+" + this.right.toString() + ")";
    }

    /**
     * Equals method.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Add obj) {
            return left.equals(obj.left) && right.equals(obj.right);
        }
        return false;
    }
}
