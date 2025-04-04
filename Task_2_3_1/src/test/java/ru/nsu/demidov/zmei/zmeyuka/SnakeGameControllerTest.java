package ru.nsu.demidov.zmei.zmeyuka;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SnakeGameControllerTest {
    @Test
    public void initializeTest() throws InterruptedException {
        SnakeGameController sampleController = new SnakeGameController();
        GameModel sampleModel = new GameModel(10, 10);
        Canvas sampleCanvas = new Canvas();
        GameView sampleView = new GameView(sampleCanvas, sampleModel);
        sampleController.setModel(sampleModel);
        sampleController.setView(sampleView);
        sampleController.setGameCanvas(sampleCanvas);
        sampleController.startGameLoop(true);
        assertEquals(sampleController.getModel().getSnake().getDirection(), Direction.RIGHT);
        KeyEvent sampleEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.UP, false, false, false, false);
        for (int i = 0; i < 100; i++) {
            sampleController.handleKeyPress(sampleEvent);
        }
        assertEquals(sampleController.getModel().getSnake().getDirection(), Direction.UP);
        assertTrue(sampleController.getView().isLostShown());
    }

}