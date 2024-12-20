package ru.nsu.demidov.expressions;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * Tests.
 */

public class NotationTest {

    @Test
    void longNotationTest() {
        String expression = "78+36*23+1/7-21+43*12-90*120/12/12/12*123-12";
        ArrayList<String> expected =
                new ArrayList<>(
                        Arrays.asList(
                                "78", "36", "23", "*", "+", "1", "7", "/", "+", "21", "-", "43",
                                "12", "*", "+", "90", "120", "*", "12", "/", "12", "/", "12", "/",
                                "123", "*", "-", "12", "-"));
        assertEquals(expected, Notation.getReversePolish(expression));
    }

    @Test
    void notationWithBracketsTest() {
        String expression = "a*(b+c)";
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("a", "b", "c", "+", "*"));
        assertEquals(expected, Notation.getReversePolish(expression));
    }

    @Test
    void notationWithLongVariablesTest() {
        String expression = "very*(long+names)";
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("very", "long", "names", "+", "*"));
        assertEquals(expected, Notation.getReversePolish(expression));
    }

    @Test
    void notationExpressionTest() {
        String expression = "very*(long+names)";
        String expected = "(very*(long+names))";
        assertEquals(expected, Notation.getExpression(expression).toString());
    }

    @Test
    void complicatedExpressionTest() {
        String expression = "x*2+(3*3)/2+1-3+10*(((28+3)*2)/2)";
        String expected = "(((((x*2)+((3*3)/2))+1)-3)+(10*(((28+3)*2)/2)))";
        assertEquals(expected, Notation.getExpression(expression).toString());
    }
}