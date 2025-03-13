package ru.nsu.demidov.dodopizzes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BakerConfigTest {

    @Test
    void testBakerConfig() {
        BakerConfig bakerConfig = new BakerConfig();
        bakerConfig.setSpeed(1488);
        assertEquals(1488, bakerConfig.getSpeed());
    }
}
