package ru.nsu.demidov.expressions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

class NumberTest {
    @Test
    public void numberTest() {
        Number sampleNumber = new Number(69);
        Number nil = new Number(0);
        assert (sampleNumber.ejaculate("x = 10") == 69);
        assert (Objects.equals((sampleNumber.derivative("x = 10")).print(), nil.print()));
        assert (Objects.equals(sampleNumber.print(), "69"));
    }
}