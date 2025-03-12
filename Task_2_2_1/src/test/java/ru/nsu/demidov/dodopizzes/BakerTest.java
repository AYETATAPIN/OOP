package ru.nsu.demidov.dodopizzes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BakerTest {

    @Test
    void testBakerProcessing() throws InterruptedException {
        OrderQueue orderQueue = new OrderQueue();
        Warehouse warehouse = new Warehouse(911);
        Baker baker = new Baker(100, orderQueue, warehouse);
        Order order = new Order(1337);
        orderQueue.placeOrder(order);
        baker.start();
        Thread.sleep(1488);
        assertEquals("READY", order.getStatus());
    }
}