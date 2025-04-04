package ru.nsu.demidov.zmei.zmeyuka;

/**
 * Direction enum.
 */

public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    /**
     * isOpposite method.
     */

    public boolean isOpposite(Direction other) {
        return (this == UP && other == DOWN)
                || (this == DOWN && other == UP)
                || (this == LEFT && other == RIGHT)
                || (this == RIGHT && other == LEFT);
    }
}