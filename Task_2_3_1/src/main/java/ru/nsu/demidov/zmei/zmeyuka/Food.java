package ru.nsu.demidov.zmei.zmeyuka;

/**
 * Food class.
 */

public class Food {
    private int xCord;
    private int yCord;
    private int value;

    public int getValue() {
        return value;
    }

    public int getxCord() {
        return xCord;
    }

    public int getyCord() {
        return yCord;
    }

    /**
     * Food constructor.
     */

    public Food(int xCord, int yCord, int value) {
        this.xCord = xCord;
        this.yCord = yCord;
        this.value = value;
    }
}