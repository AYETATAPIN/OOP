package ru.nsu.demidov.expressions;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Variable class.
 */


public class Variable extends Expression {
    private String value;

    /**
     * parseAssignation method.
     */

    private static Map<String, Integer> parseAssignations(String input) {
        Map<String, Integer> dict = new HashMap<>();
        Pattern pattern = Pattern.compile("(\\w+)\\s*=\\s*(\\d+)(;?)");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find() == true) {
            String key = matcher.group(1);
            int value = Integer.parseInt(matcher.group(2));
            dict.put(key, value);
        }
        return dict;
    }

    /**
     * Variable constructor.
     */

    public Variable(String name) {
        this.value = name;
    }

    /**
     * Derivative method.
     */

    @Override
    public Expression derivative(String variable) {
        if (variable.equals(this.value)) {
            return new Number(1);
        } else {
            return new Number(0);
        }
    }

    /**
     * Simplify method.
     */

    @Override
    public Expression simplify() {
        return new Variable(this.value);
    }

    /**
     * Evaluate method
     */

    @Override
    public double evaluate(String values) {
        Map<String, Integer> assignations = parseAssignations(values);
        return assignations.get(this.value);
    }

    /**
     * toString method.
     */

    @Override
    public String toString() {
        return this.value;
    }

    /**
     * Equals method.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Variable obj) {
            return value.equals(obj.value);
        }
        return false;
    }
}
