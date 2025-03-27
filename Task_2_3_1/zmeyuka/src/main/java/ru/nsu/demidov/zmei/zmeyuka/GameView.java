package ru.nsu.demidov.zmei.zmeyuka;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.awt.Point;

public class GameView {
    private final Canvas canvas;
    private final GameModel model;
    private final GraphicsContext graphics;

    public GameView(Canvas canvas, GameModel model) {
        this.canvas = canvas;
        this.model = model;
        this.graphics = canvas.getGraphicsContext2D();
    }

    public void render() {
        graphics.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        graphics.setFill(Color.BLUE);
        graphics.fill();
        graphics.setFill(Color.RED);
        Boolean change = false;
        for (Point point : model.getSnake().getBody()) {
            if (change == true) {
                graphics.setFill(Color.WHITE);
                change = false;
            } else {
                graphics.setFill(Color.RED);
                change = true;
            }
            graphics.fillRect(point.getX() * 20, point.getY() * 20, 20, 20);
        }
        graphics.setFill(Color.MEDIUMVIOLETRED);
        for (Point food : model.getFoods()) {
            graphics.fillOval(food.getX() * 20, food.getY() * 20, 20, 20);
        }
        graphics.drawImage(new Image(String.valueOf(getClass().getResource("pill.jpg"))), 100, 100, 100, 100);
    }
}