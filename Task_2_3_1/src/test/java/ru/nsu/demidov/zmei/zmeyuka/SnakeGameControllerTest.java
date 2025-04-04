package ru.nsu.demidov.zmei.zmeyuka;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.junit.jupiter.api.Test;


/**
 * SnakeGameControllerTest.
 */

class SnakeGameControllerTest {

    /**
     * initialize test.
     */

    @Test
    public void initializeTest() {
        SnakeGameController sampleController = new SnakeGameController();
        GameModel sampleModel = new GameModel(10, 10);
        Canvas sampleCanvas = new Canvas();
        GameView sampleView = new GameView(sampleCanvas, sampleModel);
        sampleController.setModel(sampleModel);
        sampleController.setView(sampleView);
        sampleController.setGameCanvas(sampleCanvas);
        sampleController.startGameLoop(true);
        assertEquals(sampleController.getModel().getSnake().getDirection(), Direction.RIGHT);
        KeyEvent sampleUpEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.UP,
            false, false, false, false);
        for (int i = 0; i < 100; i++) {
            sampleController.handleKeyPress(sampleUpEvent);
        }
        assertEquals(sampleController.getModel().getSnake().getDirection(), Direction.UP);
        KeyEvent sampleDownEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.DOWN,
                false, false, false, false);
        sampleController.handleKeyPress(sampleDownEvent);
        assertEquals(sampleController.getModel().getSnake().getDirection(), Direction.DOWN);
        KeyEvent sampleLeftEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.LEFT,
                false, false, false, false);
        sampleController.handleKeyPress(sampleLeftEvent);
        assertEquals(sampleController.getModel().getSnake().getDirection(), Direction.LEFT);
        KeyEvent sampleRightEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.RIGHT,
                false, false, false, false);
        sampleController.handleKeyPress(sampleRightEvent);
        assertEquals(sampleController.getModel().getSnake().getDirection(), Direction.RIGHT);
        assertTrue(sampleController.getView().isLostShown());
    }

}