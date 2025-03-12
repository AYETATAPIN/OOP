package ru.nsu.demidov.dodopizzes;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PizzeriaConfigTest {

    @Test
    void testLoadConfigFromJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        PizzeriaConfig config = objectMapper.readValue(new File("config.json"), PizzeriaConfig.class);
        assertNotNull(config);
        assertEquals(2, config.getBakers().size());
        assertEquals(2, config.getCouriers().size());
        assertEquals(10, config.getWarehouseCapacity());
    }
}