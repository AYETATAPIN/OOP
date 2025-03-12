package ru.nsu.demidov.dodopizzes;

/**
 * Pizzeria class.
 */

public class Pizzeria {
    private OrderQueue orderQueue;
    private Warehouse warehouse;
    private Baker[] bakers;
    private Courier[] couriers;

    /**
     * Pizzeria constructor.
     */

    public Pizzeria(int bakersNumber, int couriersNumber, int warehouseCapacity, int[] bakerSpeeds,
                int[] courierCapacities, int[] courierSpeeds, OrderQueue orderQueue) {
        this.orderQueue = orderQueue;
        warehouse = new Warehouse(warehouseCapacity);
        bakers = new Baker[bakersNumber];
        for (int i = 0; i < bakersNumber; i++) {
            bakers[i] = new Baker(bakerSpeeds[i], orderQueue, warehouse);
            bakers[i].start();
        }
        couriers = new Courier[couriersNumber];
        for (int i = 0; i < couriersNumber; i++) {
            couriers[i] = new Courier(courierCapacities[i], courierSpeeds[i], warehouse);
            couriers[i].start();
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