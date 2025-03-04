package ru.nsu.demidov.dodopizzes;

import java.util.Queue;
import java.util.LinkedList;

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

    public Pizzeria(int N, int M, int T, int[] bakerSpeeds, int[] courierCapacities) {
        warehouse = new Warehouse(T);
        bakers = new Baker[N];
        for (int i = 0; i < N; i++) {
            bakers[i] = new Baker(bakerSpeeds[i], orderQueue, warehouse);
            bakers[i].start();
        }
        couriers = new Courier[M];
        for (int i = 0; i < M; i++) {
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