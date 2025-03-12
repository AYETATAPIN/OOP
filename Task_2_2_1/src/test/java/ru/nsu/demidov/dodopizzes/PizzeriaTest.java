package ru.nsu.demidov.dodopizzes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PizzeriaTest {

    @Test
    void neZnayuChtoTestirovatTest() {
        OrderQueue orderQueue = new OrderQueue();
        Pizzeria pizzeria = new Pizzeria(2, 2, 1488,
            new int[] {2, 2}, new int[] {2, 2}, new int[] {2, 2}, orderQueue);
        assertNotNull(pizzeria);
        pizzeria.shutdown();
    }
}