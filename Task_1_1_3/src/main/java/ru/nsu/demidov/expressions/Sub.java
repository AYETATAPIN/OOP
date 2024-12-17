package ru.nsu.demidov.expressions;

/**
 * Sub class.
 */

public class Sub extends Expression {
    public Expression left;
    public Expression right;

    /**
     * Sub constructor.
     */

    public Sub(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Derivative method.
     */

    @Override
    public Expression derivative(String variable) {
        return new Sub(this.left.derivative(variable), this.right.derivative(variable));
    }

    /**
     * Simplify method.
     */

    @Override
    public Expression simplify() {
        Sub simplifiedSub = new Sub(this.left.simplify(), this.right.simplify());
        if (simplifiedSub.left instanceof Number leftNumber
                && simplifiedSub.right instanceof Number rightNumber) {
            return new Number(leftNumber.getValue() - rightNumber.getValue());
        } else if (simplifiedSub.left.toString().equals(simplifiedSub.right.toString())) {
            return new Number(0);
        } else {
            return simplifiedSub;
        }
    }

    /**
     * Evaluate method.
     */

    @Override
    public double evaluate(String values) {
        return this.left.evaluate(values) - this.right.evaluate(values);
    }

    /**
     * toString method.
     */

    @Override
    public String toString() {
        return "(" + this.left.toString() + "-" + this.right.toString() + ")";
    }

    /**
     * Equals method.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Sub obj) {
            return left.equals(obj.left) && right.equals(obj.right);
        }
        return false;
    }
}
