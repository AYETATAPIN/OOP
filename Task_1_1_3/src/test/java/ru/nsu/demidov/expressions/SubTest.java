package ru.nsu.demidov.expressions;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.Test;

class SubTest {
    @Test
    public void subTest() throws Exception {
        Sub sampleSub = new Sub(new Number(9), new Number(11));
        assert (Objects.equals(sampleSub.print(), "(9-11)"));
        assert (Objects.equals(sampleSub.derivative("boobs").print(), "(0-0)"));
        sampleSub = new Sub(new Variable("boobs"), new Number(5));
        assert (Objects.equals(sampleSub.derivative("boobs").print(), "(1-0)"));
        assert (Objects.equals(sampleSub.ejaculate("boobs = 74"), 69.0));
        Map<String, Integer> sampleMap = new HashMap<>();
        sampleMap.put("boobs", 74);
        assert (Objects.equals(sampleSub.ejaculate(sampleMap), 69.0));
    }
}