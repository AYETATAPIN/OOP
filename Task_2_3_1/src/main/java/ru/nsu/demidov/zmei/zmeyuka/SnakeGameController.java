package ru.nsu.demidov.zmei.zmeyuka;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

/**
 * SnakeGameController class.
 */

public class SnakeGameController {
    @FXML
    private Canvas gameCanvas;
    private GameModel model;
    private GameView view;

    /**
     * initialize method.
     */

    @FXML
    public void initialize() {
        model = new GameModel(50, 50);
        view = new GameView(gameCanvas, model);
        startGameLoop(false);
        gameCanvas.setFocusTraversable(true);
        gameCanvas.requestFocus();
    }

    /**
     * setGameCanvas method.
     */

    public void setGameCanvas(Canvas gameCanvas) {
        this.gameCanvas = gameCanvas;
    }

    /**
     * setModel method.
     */

    public void setModel(GameModel model) {
        this.model = model;
    }

    /**
     * setView method.
     */

    public void setView(GameView view) {
        this.view = view;
    }

    /**
     * getModel method.
     */

    public GameModel getModel() {
        return model;
    }

    /**
     * getView method.
     */

    public GameView getView() {
        return view;
    }

    /**
     * startGameLoop method.
     */

    public void startGameLoop(boolean isTesting) {
        new Thread(() -> {
            while (model.itsActuallyOverForYou() == false) {
                view.render();
                model.update();
                if (isTesting == false) {
                    try {
                        Thread.sleep(75);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (isTesting == false) {
                Platform.runLater(() -> {
                    view.showLost();
                    gameCanvas.getScene().setOnKeyPressed(event -> Platform.exit());

                });
            } else if (model.itsActuallyOverForYou() == true) {
                view.showLost();
            }
        }).start();
    }

    /**
     * handleKeyPress method.
     */

    @FXML
    public void handleKeyPress(KeyEvent event) {
        Direction prevDirection = model.getSnake().getDirection();
        switch (event.getCode()) {
            case UP -> {
                model.getSnake().setDirection(Direction.UP);
            }
            case DOWN -> {
                model.getSnake().setDirection(Direction.DOWN);
            }
            case LEFT -> {
                model.getSnake().setDirection(Direction.LEFT);
            }
            case RIGHT -> {
                model.getSnake().setDirection(Direction.RIGHT);
            }
            default -> {
            }
        }
        if (prevDirection.toString().equals(event.getCode().toString()) == false) {
            model.getSnake().move();
        }
    }
}