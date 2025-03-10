package ru.nsu.demidov.dodopizzes;

import java.util.Queue;

/**
 * Baker class.
 */

public class Baker extends Thread {
    private int speed;
    private Queue<Order> orderQueue;
    private Warehouse warehouse;

    /**
     * Baker constructor.
     */

    public Baker(int speed, Queue<Order> orderQueue, Warehouse warehouse) {
        this.speed = speed;
        this.orderQueue = orderQueue;
        this.warehouse = warehouse;
    }

    /**
     * run method.
     */

    @Override
    public void run() {
        while (true) {
            Order order;
            synchronized (orderQueue) {
                while (orderQueue.isEmpty()) {
                    try {
                        orderQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                order = orderQueue.poll();
            }
            order.setStatus("PREPARING");
            System.out.println(order);
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            order.setStatus("READY");
            System.out.println("Order " + order.getOrderId() + " " + order.getStatus());
            warehouse.addOrder(order);
        }
    }
}