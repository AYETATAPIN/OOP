package ru.nsu.demidov.zmei.task_2_3_1;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class SnakeLengthProperty {
    private IntegerProperty snakeLength = new SimpleIntegerProperty();

    public final void setSnakeLength(int value) {
        snakeLength.set(value);
    }

    public IntegerProperty snakeLengthProperty() {
        return snakeLength;
    }
}