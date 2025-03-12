package ru.nsu.demidov.dodopizzes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import org.junit.jupiter.api.Test;

class WarehouseTest {

    @Test
    void orderTest() {
        Warehouse warehouse = new Warehouse(2);
        Order order1 = new Order(1488);
        Order order2 = new Order(69);
        warehouse.addOrder(order1);
        warehouse.addOrder(order2);
        List<Order> orders = warehouse.getOrders(2);
        assertEquals(2, orders.size());
    }

    @Test
    void endTest() {
        Warehouse warehouse = new Warehouse(1);
        Thread thread = new Thread(() -> {
            List<Order> orders = warehouse.getOrders(1);
            assertNull(orders);
        });
        thread.start();
        thread.interrupt();
    }
}