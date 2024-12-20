package ru.nsu.demidov.expressions;

/**
 * Div class.
 */

public class Div extends Expression {
    private Expression left;
    private Expression right;

    /**
     * Div constructor.
     */

    public Div(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Derivative method.
     */

    @Override
    public Expression derivative(String variable) {
        return new Div(
                new Sub(
                        new Mul(this.left, this.right.derivative(variable)),
                        new Mul(this.left.derivative(variable), this.right)),
                new Mul(this.right, this.right));
    }

    /**
     * Simplify method.
     */

    @Override
    public Expression simplify() {
        Div simplifiedDiv = new Div(this.left.simplify(), this.right.simplify());
        if (simplifiedDiv.left instanceof Number leftNumber
                && simplifiedDiv.right instanceof Number rightNumber) {
            return new Number(leftNumber.getValue() / rightNumber.getValue());
        } else if (this.left instanceof Number leftNumber && leftNumber.getValue() == 0) {
            return new Number(0);
        } else {
            return simplifiedDiv;
        }
    }

    /**
     * Evaluate method.
     */

    @Override
    public double evaluate(String values) {
        double leftResult = this.left.evaluate(values);
        double rightResult = this.right.evaluate(values);
        if (rightResult == 0) {
            throw new ArithmeticException("How dare you divide by zero");
        }
        return leftResult / rightResult;
    }

    /**
     * toString method.
     */

    @Override
    public String toString() {
        return "(" + this.left.toString() + "/" + this.right.toString() + ")";
    }

    /**
     * Equals method.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Div odiv) {
            return left.equals(odiv.left) && right.equals(odiv.right);
        }
        return false;
    }
}
