package ru.nsu.demidov.expressions;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.Test;

class VariableTest {
    @Test
    public void variableTest() throws Exception {
        Variable sampleVariable = new Variable("boobs");
        assert (Objects.equals(sampleVariable.derivative("boobs").print(), "1"));
        assert (Objects.equals(sampleVariable.derivative("knockers").print(), "0"));
        assert (sampleVariable.ejaculate("boobs = 5") == 5);
        Map<String, Integer> sampleMap = new HashMap<>();
        sampleMap.put("boobs", 64);
        assert (Objects.equals(sampleVariable.ejaculate(sampleMap), 64.0));
        //assert (sampleVariable.ejaculate("knockers = 5") == 5); - tak ne nado
        Exception sampleException = new Exception("boobs doesn't have a value");
        boolean isException = false;
        try {
            sampleVariable.ejaculate("knockers = 5");
        } catch (Exception caughtException) {
            if (Objects.equals(caughtException.getMessage(), sampleException.getMessage())) {
                isException = true;
            }
        }
        assert (isException == true); // nado vot tak
    }
}