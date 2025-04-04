package ru.nsu.demidov.zmei.task_2_3_1;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;


public class SnakeGameController {
    @FXML
    private Canvas gameCanvas;
    private GameModel model;
    private GameView view;

    @FXML
    public void initialize() {
        model = new GameModel(50, 50);
        view = new GameView(gameCanvas, model);
        startGameLoop();
        gameCanvas.setFocusTraversable(true);
        gameCanvas.requestFocus();
    }

    private void startGameLoop() {
        new Thread(() -> {
            while (model.itsActuallyOverForYou() == false) {
                view.render();
                model.update();
                try {
                    Thread.sleep(75);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Platform.runLater(() -> {
                view.showLost();
                gameCanvas.getScene().setOnKeyPressed(event -> Platform.exit());

            });
        }).start();
    }

    @FXML
    private void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case UP -> {
                model.getSnake().setDirection(Direction.UP);
                model.getSnake().move();
            }
            case DOWN -> {
                model.getSnake().setDirection(Direction.DOWN);
                model.getSnake().move();
            }
            case LEFT -> {
                model.getSnake().setDirection(Direction.LEFT);
                model.getSnake().move();
            }
            case RIGHT -> {
                model.getSnake().setDirection(Direction.RIGHT);
                model.getSnake().move();
            }
        }
    }
}