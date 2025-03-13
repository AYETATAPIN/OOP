package ru.nsu.demidov.dodopizzes;

/**
 * Order class.
 */

public class Order {
    private int orderId;
    private String status;

    /**
     * Order constructor.
     */

    public Order(int orderId) {
        this.orderId = orderId;
        this.status = "CREATED";
    }

    /**
     * getOrderId method.
     */

    public int getOrderId() {
        return orderId;
    }

    /**
     * getStatus method.
     */

    public String getStatus() {
        return status;
    }

    /**
     * setStatus method.
     */

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return ("Order " + this.getOrderId() + " " + this.getStatus());
    }
}