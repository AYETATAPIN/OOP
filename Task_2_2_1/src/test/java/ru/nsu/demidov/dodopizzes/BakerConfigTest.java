package ru.nsu.demidov.dodopizzes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BakerConfigTest {

    @Test
    void testBakerConfig() {
        BakerConfig bakerConfig = new BakerConfig();
        bakerConfig.setSpeed(1488);
        assertEquals(1488, bakerConfig.getSpeed());
    }
}
