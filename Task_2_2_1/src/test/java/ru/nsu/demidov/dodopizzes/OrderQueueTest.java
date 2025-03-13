package ru.nsu.demidov.dodopizzes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class OrderQueueTest {

    @Test
    void pollTest() {
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order(1488);
        orderQueue.placeOrder(order);
        Order polledOrder = orderQueue.pollOrder();
        assertNotNull(polledOrder);
        assertEquals(1488, polledOrder.getOrderId());
    }

    @Test
    void endTest() {
        OrderQueue orderQueue = new OrderQueue();
        Thread thread = new Thread(() -> {
            Order polledOrder = orderQueue.pollOrder();
            assertNull(polledOrder);
        });
        thread.start();
        thread.interrupt();
    }
}