package ru.nsu.demidov.expressions;

import java.util.Objects;
import org.junit.jupiter.api.Test;

class NumberTest {
    @Test
    public void numberTest() {
        Number sampleNumber = new Number(69);
        Number nil = new Number(0);
        Number sampleFloatingNumber = new Number(14.88);
        assert (sampleNumber.ejaculate("x = 10") == 69);
        assert (Objects.equals((sampleNumber.derivative("x = 10")).print(), nil.print()));
        assert (Objects.equals(sampleNumber.print(), "69"));
        assert (Objects.equals(sampleFloatingNumber.print(), "14.88"));
    }
}