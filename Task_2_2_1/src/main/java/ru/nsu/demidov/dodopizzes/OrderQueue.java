package ru.nsu.demidov.dodopizzes;

import java.util.LinkedList;
import java.util.Queue;

/**
 * OrderQueue class.
 */

public class OrderQueue {

    private Queue<Order> orderQueue = new LinkedList<>();

    /**
     * pollOrder method.
     */

    public Order pollOrder() {
        synchronized (orderQueue) {
            while (orderQueue.isEmpty()) {
                try {
                    orderQueue.wait();
                } catch (InterruptedException e) {
                    break;
                }
            }
            return (orderQueue.poll());
        }
    }

    /**
     * add method.
     */

    public void add(Order order) {
        this.orderQueue.add(order);
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
}
