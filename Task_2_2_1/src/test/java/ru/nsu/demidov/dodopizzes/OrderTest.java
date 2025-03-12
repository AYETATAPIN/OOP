package ru.nsu.demidov.dodopizzes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class OrderTest {

    @Test
    void orderTest() {
        Order order = new Order(1488);
        assertEquals(1488, order.getOrderId());
        assertEquals("CREATED", order.getStatus());
    }

    @Test
    void statusTest() {
        Order order = new Order(1);
        order.setStatus("PREPARING");
        assertEquals("PREPARING", order.getStatus());
    }

    @Test
    void toStringTest() {
        Order order = new Order(1);
        order.setStatus("PREPARING");
        assertEquals("Order 1 PREPARING", order.toString());
    }
}