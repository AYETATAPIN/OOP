package ru.nsu.demidov.zmei.zmeyuka;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * SnakeLengthProperty class.
 */

public class SnakeLengthProperty {
    private IntegerProperty snakeLength = new SimpleIntegerProperty();

    /**
     * setSnakeLength method.
     */

    public final void setSnakeLength(int value) {
        snakeLength.set(value);
    }

    /**
     * snakeLengthProperty method.
     */

    public IntegerProperty snakeLengthProperty() {
        return snakeLength;
    }
}