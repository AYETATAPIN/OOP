package ru.nsu.demidov.zmei.zmeyuka;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Point;
import org.junit.jupiter.api.Test;

class SnakeTest {
    @Test
    public void moveTest() {
        Snake sampleSnake = new Snake(6, 9);
        assertEquals(sampleSnake.getDirection(), Direction.RIGHT);
        sampleSnake.setDirection(Direction.LEFT);
        assertEquals(sampleSnake.getDirection(), Direction.RIGHT);
        sampleSnake.setDirection(Direction.UP);
        assertEquals(sampleSnake.getDirection(), Direction.UP);
        Point prevHead = new Point((int) sampleSnake.getHead().getX(),
            (int) sampleSnake.getHead().getY());
        prevHead.setLocation(prevHead.getX(), prevHead.getY() - 1);
        sampleSnake.move();
        assertEquals(prevHead.getLocation(), sampleSnake.getHead().getLocation());
    }

    @Test
    public void growTest() {
        Snake sampleSnake = new Snake(6, 6);
        int prevLen = sampleSnake.lengthProperty().snakeLengthProperty().get();
        sampleSnake.grow();
        assertEquals(sampleSnake.lengthProperty().snakeLengthProperty().get(), prevLen + 1);
    }

}