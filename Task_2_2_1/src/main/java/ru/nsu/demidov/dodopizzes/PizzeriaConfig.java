package ru.nsu.demidov.dodopizzes;

import java.util.List;

/**
 * PizzeriaConfig class.
 */

public class PizzeriaConfig {
    private List<BakerConfig> bakers;
    private List<CourierConfig> couriers;
    private int warehouseCapacity;
    private int workTime;

    /**
     * getWorkTime method.
     */

    public int getWorkTime() {
        return workTime;
    }

    /**
     * getBakers method.
     */

    public List<BakerConfig> getBakers() {
        return bakers;
    }

    /**
     * getCouriers method.
     */

    public List<CourierConfig> getCouriers() {
        return couriers;
    }

    /**
     * getWarehouseCapacity method.
     */

    public int getWarehouseCapacity() {
        return warehouseCapacity;
    }
}
