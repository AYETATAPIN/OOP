package ru.nsu.demidov.dodopizzes;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Pizzeria class.
 */

public class Pizzeria {
    private Queue<Order> orderQueue = new LinkedList<>();
    private Warehouse warehouse;
    private Baker[] bakers;
    private Courier[] couriers;

    /**
     * Pizzeria constructor.
     */

    public Pizzeria(int bakersNumber, int couriersNumber, int warehouseCapacity,
                int[] bakerSpeeds, int[] courierCapacities) {
        warehouse = new Warehouse(warehouseCapacity);
        bakers = new Baker[bakersNumber];
        for (int i = 0; i < bakersNumber; i++) {
            bakers[i] = new Baker(bakerSpeeds[i], orderQueue, warehouse);
            bakers[i].start();
        }
        couriers = new Courier[couriersNumber];
        for (int i = 0; i < couriersNumber; i++) {
            couriers[i] = new Courier(courierCapacities[i], warehouse);
            couriers[i].start();
        }
    }

    /**
     * placeOrder method.
     */

    public void placeOrder(Order order) {
        synchronized (orderQueue) {
            orderQueue.add(order);
            orderQueue.notifyAll();
        }
    }

    /**
     * shutdown method.
     */

    public void shutdown() {
        for (Baker baker : bakers) {
            baker.interrupt();
        }
        for (Courier courier : couriers) {
            courier.interrupt();
        }
    }
}