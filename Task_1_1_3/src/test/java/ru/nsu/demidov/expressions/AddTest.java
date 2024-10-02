package ru.nsu.demidov.expressions;

import java.util.Objects;
import org.junit.jupiter.api.Test;

class AddTest {
    @Test
    public void addTest() {
        Add sampleAdd = new Add(new Number(9), new Number(11));
        assert (Objects.equals(sampleAdd.print(), "(9+11)"));
        assert (Objects.equals(sampleAdd.derivative("boobs").print(), "(0+0)"));
        sampleAdd = new Add(new Variable("boobs"), new Number(5));
        assert (Objects.equals(sampleAdd.derivative("boobs").print(), "(1+0)"));
    }
}