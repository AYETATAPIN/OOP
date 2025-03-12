package ru.nsu.demidov.dodopizzes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CourierTest {

    @Test
    void testCourierDelivery() throws InterruptedException {
        Warehouse warehouse = new Warehouse(10);
        Courier courier = new Courier(731, 100, warehouse);
        Order order1 = new Order(1488);
        Order order2 = new Order(69);
        warehouse.addOrder(order1);
        warehouse.addOrder(order2);
        courier.start();
        Thread.sleep(731);
        assertEquals("DELIVERED", order1.getStatus());
        assertEquals("DELIVERED", order2.getStatus());
    }
}