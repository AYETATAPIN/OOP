package ru.nsu.demidov.expressions;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.Test;

class MulTest {
    @Test
    public void mulTest() throws Exception {
        Mul sampleMul = new Mul(new Number(9), new Number(11));
        assert (Objects.equals(sampleMul.print(), "(9*11)"));
        assert (Objects.equals(sampleMul.derivative("boobs").print(), "((0*11)+(9*0))"));
        sampleMul = new Mul(new Variable("boobs"), new Number(5));
        assert (Objects.equals(sampleMul.derivative("boobs").print(), "((1*5)+(boobs*0))"));
        assert (Objects.equals(sampleMul.ejaculate("boobs = 2"), 10.0));
        Map<String, Integer> sampleMap = new HashMap<>();
        sampleMap.put("boobs", 2);
        assert (Objects.equals(sampleMul.ejaculate(sampleMap), 10.0));
    }
}