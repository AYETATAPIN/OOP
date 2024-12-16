package ru.nsu.demidov.expressions;

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Polish notation class.
 */

public class Notation {

    /**
     * isOperator method.
     */

    private static boolean isOperator(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')');
    }

    /**
     * getNextToken method.
     */

    private static String getNextToken(String expression, int index) {
        char firstSymbol = expression.charAt(index);
        if (isOperator(firstSymbol)) {
            return "" + firstSymbol;
        } else if (Character.isDigit(firstSymbol)) {
            StringBuilder result = new StringBuilder();
            do {
                result.append(expression.charAt(index));
                index += 1;
            } while (index < expression.length() && Character.isDigit(expression.charAt(index)));
            return result.toString();
        } else {
            StringBuilder res = new StringBuilder();
            do {
                res.append(expression.charAt(index));
                index += 1;
            } while (index < expression.length() && !Character.isDigit(expression.charAt(index))
                    && !isOperator(expression.charAt(index)));
            return res.toString();
        }
    }

    /**
     * getReversePolish method.
     */

    public static List<String> getReversePolish(String expression) {
        List<String> output = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();
        int index = 0;
        while (index != expression.length()) {
            String token = getNextToken(expression, index);
            index += token.length();
            if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                try {
                    String topElement = stack.pop();
                    while (topElement.equals("(") == false) {
                        output.add(topElement);
                        topElement = stack.pop();
                    }
                } catch (NoSuchElementException e) {
                    throw new IllegalArgumentException("Mismatched parentheses");
                }
            } else if (token.equals("*") || token.equals("/")) {
                if (stack.isEmpty() == false) {
                    String topToken = stack.peek();
                    while (topToken.equals("*") || topToken.equals("/")) {
                        output.add(stack.pop());
                        if (stack.isEmpty()) {
                            break;
                        }
                        topToken = stack.peek();
                    }
                }
                stack.push(token);
            } else if (token.equals("+") || token.equals("-")) {
                if (stack.isEmpty() == false) {
                    String topToken = stack.peek();
                    while (topToken.equals("*") || topToken.equals("/") || topToken.equals("+")
                            || topToken.equals("-")) {
                        output.add(stack.pop());
                        if (stack.isEmpty()) {
                            break;
                        }
                        topToken = stack.peek();
                    }
                }
                stack.push(token);
            } else {
                output.add(token);
            }
        }
        while (stack.isEmpty() == false) {
            String operator = stack.pop();
            if (operator.equals("(")) {
                throw new IllegalArgumentException("Mismatched parentheses");
            }
            output.add(operator);
        }
        return output;
    }

    /**
     * getExpression method.
     */

    public static Expression getExpression(String expression) {
        List<String> tokens = getReversePolish(expression);
        Deque<Expression> result = new ArrayDeque<>();
        for (String token : tokens) {
            if (Character.isDigit(token.charAt(0))) {
                result.push(new Number(Double.parseDouble(token)));
            } else if (token.equals("+")) {
                Expression rightExpression = result.pop();
                Expression leftExpression = result.pop();
                result.push(new Sum(leftExpression, rightExpression));
            } else if (token.equals("-")) {
                Expression rightExpression = result.pop();
                Expression leftExpression = result.pop();
                result.push(new Sub(leftExpression, rightExpression));
            } else if (token.equals("*")) {
                Expression rightExpression = result.pop();
                Expression leftExpression = result.pop();
                result.push(new Mul(leftExpression, rightExpression));
            } else if (token.equals("/")) {
                Expression rightExpression = result.pop();
                Expression leftExpression = result.pop();
                result.push(new Div(leftExpression, rightExpression));
            } else {
                result.push(new Variable(token));
            }
        }
        return result.pop();
    }
}