package ru.nsu.demidov.dodopizzes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CourierConfigTest {

    @Test
    void testCourierConfig() {
        CourierConfig courierConfig = new CourierConfig();
        courierConfig.setCapacity(1488);
        assertEquals(1488, courierConfig.getCapacity());
    }
}