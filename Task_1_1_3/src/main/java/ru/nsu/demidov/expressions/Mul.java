package ru.nsu.demidov.expressions;

/**
 * Expression class.
 */

public class Mul extends Expression {
    public Expression left;
    public Expression right;

    /**
     * Mul constructor.
     */

    public Mul(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Derivative method.
     */

    @Override
    public Expression derivative(String variable) {
        return new Sum(
                new Mul(this.left.derivative(variable), this.right),
                new Mul(this.left, this.right.derivative(variable)));
    }

    /**
     * Simplify method.
     */

    @Override
    public Expression simplify() {
        Mul simplifiedMul = new Mul(this.left.simplify(), this.right.simplify());
        if (simplifiedMul.left instanceof Number leftNumber
                && simplifiedMul.right instanceof Number rightNumber) {
            return new Number(leftNumber.getValue() * rightNumber.getValue());
        } else if (simplifiedMul.left instanceof Number leftNumber && leftNumber.getValue() == 0
                || simplifiedMul.right instanceof Number rightNumber
                && rightNumber.getValue() == 0) {
            return new Number(0);
        } else if (simplifiedMul.left instanceof Number leftNumber && leftNumber.getValue() == 1) {
            return simplifiedMul.right;
        } else if (simplifiedMul.right instanceof Number rightNumber
                && rightNumber.getValue() == 1) {
            return simplifiedMul.left;
        } else {
            return simplifiedMul;
        }
    }

    /**
     * Evaluate method.
     */

    @Override
    public double evaluate(String values) {
        return this.left.evaluate(values) * this.right.evaluate(values);
    }

    /**
     * toString method.
     */

    @Override
    public String toString() {
        return "(" + this.left.toString() + "*" + this.right.toString() + ")";
    }

    /**
     * Equals method.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Mul omult) {
            return left.equals(omult.left) && right.equals(omult.right);
        }
        return false;
    }
}
