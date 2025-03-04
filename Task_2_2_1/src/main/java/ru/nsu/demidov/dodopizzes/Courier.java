package ru.nsu.demidov.dodopizzes;

import java.util.List;

/**
 * Courier class.
 */

public class Courier extends Thread {
    private int capacity;
    private Warehouse warehouse;

    /**
     * Courier constructor.
     */

    public Courier(int capacity, Warehouse warehouse) {
        this.capacity = capacity;
        this.warehouse = warehouse;
    }

    /**
     * run method.
     */

    @Override
    public void run() {
        while (true) {
            List<Order> orders = warehouse.getOrders(capacity);
            for (Order order : orders) {
                order.setStatus("DELIVERING");
                System.out.println("Order " + order.getOrderId() + " " + order.getStatus());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                order.setStatus("DELIVERED");
                System.out.println("Order " + order.getOrderId() + " " + order.getStatus());
            }
        }
    }
}