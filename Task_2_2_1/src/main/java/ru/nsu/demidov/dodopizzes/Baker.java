package ru.nsu.demidov.dodopizzes;

/**
 * Baker class.
 */

public class Baker extends Thread {
    private int speed;
    private OrderQueue orderQueue;
    private Warehouse warehouse;

    /**
     * Baker constructor.
     */

    public Baker(int speed, OrderQueue orderQueue, Warehouse warehouse) {
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
            Order order = orderQueue.pollOrder();
            if (order == null) {
                break;
            }
            order.setStatus("PREPARING");
            System.out.println(order);
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                break;
            }
            order.setStatus("READY");
            System.out.println(order);
            warehouse.addOrder(order);
        }
    }
}