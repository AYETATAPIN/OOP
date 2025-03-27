package ru.nsu.demidov.zmei.zmeyuka;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
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
        model.setView(view);
        startGameLoop();
        gameCanvas.setFocusTraversable(true);
        gameCanvas.requestFocus();
    }

    private void startGameLoop() {
        new Thread(() -> {
            while (model.isOver() == false) {
                model.update();
                //view.render();
                try {
                    Thread.sleep(75);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Game Over");
                alert.setHeaderText("Вы проиграли!");
                alert.setContentText("Змейка столкнулась с собой или границей.");
                alert.showAndWait();
            });
        }).start();

        new Thread(() -> {
            while (true) {
                view.render();
                /*try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        }).start();
    }

    @FXML
    private void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case UP -> model.getSnake().setDirection(Direction.UP);
            case DOWN -> model.getSnake().setDirection(Direction.DOWN);
            case LEFT -> model.getSnake().setDirection(Direction.LEFT);
            case RIGHT -> model.getSnake().setDirection(Direction.RIGHT);
        }
    }
}