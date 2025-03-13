package ru.nsu.demidov.dodopizzes;

import org.junit.jupiter.api.Test;

class PizzeriaTest {

    @Test
    void neZnayuChtoTestirovatTest() {
        OrderQueue orderQueue = new OrderQueue();
        Pizzeria pizzeria = new Pizzeria(2, 2, 1488,
                new int[]{2, 2}, new int[]{2, 2}, new int[]{2, 2}, orderQueue);
        for (int i = 1; i < 1488; i++) {
            Order sampleOrder = new Order(i);
            orderQueue.placeOrder(sampleOrder);
            if (i == 10) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pizzeria.shutdown();
    }
}