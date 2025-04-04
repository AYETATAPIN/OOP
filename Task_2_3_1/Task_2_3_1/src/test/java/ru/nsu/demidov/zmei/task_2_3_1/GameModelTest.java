package ru.nsu.demidov.zmei.task_2_3_1;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class GameModelTest {
    @Test
    public void foodTest() {
        GameModel sampleModel = new GameModel(10, 10);
        LinkedList<Food> sampleFoods = new LinkedList<>();
        Food sampleFood = new Food(6, 5, 2);
        sampleFoods.add(sampleFood);
        sampleModel.setFoods(sampleFoods);
        int prevLen = sampleModel.getSnake().lengthProperty().snakeLengthProperty().get();
        sampleModel.update();
        assertEquals(prevLen + 2, sampleModel.getSnake().lengthProperty().snakeLengthProperty().get());
        assertNotEquals(sampleFood, sampleModel.getFoods().getFirst());
    }

    @Test
    public void collisionTest() {
        GameModel sampleModel = new GameModel(10, 10);
        assertFalse(sampleModel.itsActuallyOverForYou());
        for (int i = 0; i < 69; ++i) {
            sampleModel.update();
        }
        assertTrue(sampleModel.itsActuallyOverForYou());
    }

}