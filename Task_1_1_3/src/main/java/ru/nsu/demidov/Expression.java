package ru.nsu.demidov;

import java.util.HashMap;
import java.util.Map;

/**
 * Expression class.
 */


public abstract class Expression {
    Map<String, Integer> values;

    /**
     * Derivative of expression.
     */

    public abstract Expression derivative(String variable);

    /**
     * Evaluation of expression.
     */

    public abstract int ejaculate(String ejaculateballs);

    /**
     * Output of expression.
     */

    public abstract void print();

    /**
     * Evaluations parser.
     */

    public void ballsParser(String ejaculateballs) {
        if (this.values == null) {
            this.values = new HashMap<>();
        }
        ejaculateballs = ejaculateballs.replace('=', ' ').replace(';', ' ');
        ejaculateballs = ejaculateballs.replace("   ", " ").replace("  ", " ");
        String[] balls = ejaculateballs.split(" ");
        int size = balls.length;
        for (int i = 0; i < size; i += 2) {
            this.values.put(balls[i], Integer.valueOf(balls[i + 1]));
        }
    }
}
