package ru.nsu.demidov.zmei.zmeyuka;

/**
 * Food class.
 */

public class Food {
    private int abscsissAxisCord;
    private int ordinateAxisCord;
    private int value;

    public int getValue() {
        return value;
    }

    public int getAbscsissAxisCord() {
        return abscsissAxisCord;
    }

    public int getOrdinateAxisCord() {
        return ordinateAxisCord;
    }

    /**
     * Food constructor.
     */

    public Food(int abscsissAxisCord, int ordinateAxisCord, int value) {
        this.abscsissAxisCord = abscsissAxisCord;
        this.ordinateAxisCord = ordinateAxisCord;
        this.value = value;
    }
}