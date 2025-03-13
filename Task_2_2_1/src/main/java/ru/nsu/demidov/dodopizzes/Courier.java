package ru.nsu.demidov.dodopizzes;

import java.util.List;

/**
 * Courier class.
 */

public class Courier extends Thread {
    private int capacity;
    private int speed;
    private Warehouse warehouse;

    /**
     * Courier constructor.
     */

    public Courier(int capacity, int speed, Warehouse warehouse) {
        this.capacity = capacity;
        this.speed = speed;
        this.warehouse = warehouse;
    }

    /**
     * run method.
     */

    @Override
    public void run() {
        while (true) {
            List<Order> orders = warehouse.getOrders(capacity);
            if (orders == null) {
                break;
            }
            for (Order order : orders) {
                order.setStatus("DELIVERING");
                System.out.println(order);
                try {
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    break;
                }
                order.setStatus("DELIVERED");
                System.out.println("Order " + order.getOrderId() + " " + order.getStatus());
            }
        }
    }
}