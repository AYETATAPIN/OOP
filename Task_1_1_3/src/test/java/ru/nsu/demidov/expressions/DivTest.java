package ru.nsu.demidov.expressions;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.Test;

class DivTest {
    @Test
    public void divTest() throws Exception {
        Div sampleDiv = new Div(new Number(9), new Number(1));
        assert (Objects.equals(sampleDiv.print(), "(9/1)"));
        assert (Objects.equals(sampleDiv.derivative("boobs").print(), "(((0*1)-(9*0))/(9*9))"));
        sampleDiv = new Div(new Variable("boobs"), new Number(1));
        assert (Objects.equals(sampleDiv.derivative("boobs").print(), "(((1*1)-(boobs*0))/(boobs*boobs))"));
        assert (Objects.equals(sampleDiv.ejaculate("boobs = 2"), 2.0));
        Map<String, Integer> sampleMap = new HashMap<>();
        sampleMap.put("boobs", 2);
        assert (Objects.equals(sampleDiv.ejaculate(sampleMap), 2.0));
    }
}