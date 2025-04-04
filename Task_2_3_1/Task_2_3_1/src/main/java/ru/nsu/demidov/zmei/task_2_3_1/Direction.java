package ru.nsu.demidov.zmei.task_2_3_1;

public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    public boolean isOpposite(Direction other) {
        return (this == UP && other == DOWN) ||
                (this == DOWN && other == UP) ||
                (this == LEFT && other == RIGHT) ||
                (this == RIGHT && other == LEFT);
    }
}