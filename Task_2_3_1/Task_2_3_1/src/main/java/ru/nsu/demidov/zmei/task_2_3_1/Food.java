package ru.nsu.demidov.zmei.task_2_3_1;

public class Food {
    private int x;
    private int y;
    private int value;

    public int getValue() {
        return value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Food(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }
}