package ru.nsu.demidov.dodopizzes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class PizzeriaConfigTest {

    @Test
    void testLoadConfigFromJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String params = "{\n"
                + "  \"bakers\": [\n"
                + "    {\"speed\": 20},\n"
                + "    {\"speed\": 30}\n"
                + "  ],\n"
                + "  \"couriers\": [\n"
                + "    {\"capacity\": 2, \"speed\": 20},\n"
                + "    {\"capacity\": 3, \"speed\": 15}\n"
                + "  ],\n"
                + "  \"warehouseCapacity\": 10,\n"
                + "  \"workTime\": 2000\n"
                + "}";
        PizzeriaConfig config = objectMapper.readValue(params, PizzeriaConfig.class);
        assertNotNull(config);
        assertEquals(2, config.getBakers().size());
        assertEquals(2, config.getCouriers().size());
        assertEquals(10, config.getWarehouseCapacity());
    }
}