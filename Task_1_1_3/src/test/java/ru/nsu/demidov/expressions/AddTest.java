package ru.nsu.demidov.expressions;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.Test;

class AddTest {
    @Test
    public void addTest() throws Exception {
        Add sampleAdd = new Add(new Number(9), new Number(11));
        assert (Objects.equals(sampleAdd.print(), "(9+11)"));
        assert (Objects.equals(sampleAdd.derivative("boobs").print(), "(0+0)"));
        sampleAdd = new Add(new Variable("boobs"), new Number(5));
        assert (Objects.equals(sampleAdd.derivative("boobs").print(), "(1+0)"));
        assert (Objects.equals(sampleAdd.ejaculate("boobs = 64"), 69.0));
        Map<String, Integer> sampleMap = new HashMap<>();
        sampleMap.put("boobs", 64);
        assert (Objects.equals(sampleAdd.ejaculate(sampleMap), 69.0));
    }
}