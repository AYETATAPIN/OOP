package ru.nsu.demidov.dodopizzes;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Warehouse class.
 */

public class Warehouse {
    private int capacity;
    private Queue<Order> orders = new LinkedList<>();

    /**
     * Warehouse constructor.
     */

    public Warehouse(int capacity) {
        this.capacity = capacity;
    }

    /**
     * addOrder method.
     */

    public synchronized void addOrder(Order order) {
        while (orders.size() >= capacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                return;
            }
        }
        orders.add(order);
        notifyAll();
    }

    /**
     * getOrders method.
     */

    public synchronized List<Order> getOrders(int maxOrders) {
        while (orders.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        List<Order> result = new LinkedList<>();
        for (int i = 0; i < maxOrders && orders.isEmpty() == false; i++) {
            result.add(orders.poll());
        }
        notifyAll();
        return result;
    }
}
