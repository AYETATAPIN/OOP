package ru.nsu.demidov.expressions;

import java.util.Objects;
import org.junit.jupiter.api.Test;

class AddTest {
    @Test
    public void addTest() {
        Add sampleAdd = new Add(new Number(9), new Number(11));
        assert (Objects.equals(sampleAdd.print(), "(9+11)"));
        assert (Objects.equals(sampleAdd.derivative("x = 10").print(), "(0+0)"));
    }
}