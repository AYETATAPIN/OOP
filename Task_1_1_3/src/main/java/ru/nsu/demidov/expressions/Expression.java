package ru.nsu.demidov.expressions;

import java.util.HashMap;
import java.util.Map;

/**
 * Expression class.
 */


public abstract class Expression {

    /**
     * Derivative of expression.
     */

    public abstract Expression derivative(String variable);

    /**
     * Evaluation of expression.
     */

    public abstract int ejaculate(String ejaculateballs) throws Exception;

    /**
     * Output of expression.
     */

    public abstract void print();

    /**
     * Evaluations parser.
     */

    public static Map<String, Integer> ballsParser(String ejaculateballs) {
        Map<String, Integer> values = new HashMap<>();
        ejaculateballs = ejaculateballs.replace('=', ' ').replace(';', ' ');
        ejaculateballs = ejaculateballs.replace("   ", " ").replace("  ", " ");
        String[] balls = ejaculateballs.split(" ");
        int size = balls.length;
        for (int i = 0; i < size; i += 2) {
            values.put(balls[i], Integer.valueOf(balls[i + 1]));
        }
        return values;
    }
}
