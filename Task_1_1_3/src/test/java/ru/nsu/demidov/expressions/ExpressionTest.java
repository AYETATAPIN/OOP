package ru.nsu.demidov.expressions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Tests.
 */

public class ExpressionTest {

    @Test
    void testEqual() {
        assertEquals(Notation.getExpression("(2+x*3)*x"), Notation.getExpression("(2+x*3)*x"));
    }

    @Test
    void testNotEqual() {
        assertNotEquals(Notation.getExpression("(2+x*4)*x"), Notation.getExpression("(2+x*3)*x"));
    }

    @Test
    void testVariables() {
        assertEquals(
                10,
                Notation.getExpression("(x+y-long)*story/short").evaluate("x = 3; y = 8; " +
                    "long = 1; story = 2; short = 2"));
    }

    @Test
    void testEvaluate() {
        assertEquals(180,
                Notation.getExpression("(((x+y)-2+x)*z)*3").evaluate("x = 3; y = 8; z = 5"));
    }

    @Test
    void testExpressions() {
        assertEquals("(((0+((1*3)+(x*0)))*x)+((2+(x*3))*1))",
                Notation.getExpression("(2+x*3)*x").derivative("x").toString());
    }

    @Test
    void testDerivative() {
        assertEquals("(0+((0*x)+(2*1)))",
                Notation.getExpression("(3+(2*x))").derivative("x").toString());
    }


    @Test
    void testDiv() {
        assertEquals("((((5-x)*0)-((0-1)*2))/(2*2))",
                Notation.getExpression("(5-x)/2").derivative("x").toString());
        Expression exp = new Div(new Variable("x"), new Variable("y"));
        assertEquals("(x/y)", exp.simplify().toString());
        assertEquals(2, exp.evaluate("x = 10; y = 5"));
        assertEquals("(((x*0)-(1*y))/(y*y))", exp.derivative("x").toString());
    }

    @Test
    void testAdd() {
        Expression exp = new Add(new Variable("x"), new Variable("y"));
        assertEquals("(x+y)", exp.simplify().toString());
        assertEquals(25, exp.evaluate("x = 10; y = 15"));
        assertEquals("(1+0)", exp.derivative("x").toString());
    }

    @Test
    void testSub() {
        Expression exp = new Sub(new Variable("x"), new Variable("y"));
        assertEquals("(x-y)", exp.simplify().toString());
        assertEquals(-5, exp.evaluate("x = 10; y = 15"));
        assertEquals("(1-0)", exp.derivative("x").toString());
    }
}