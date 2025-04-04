package ru.nsu.demidov.zmei.task_2_3_1;

import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.awt.Point;
import java.util.LinkedList;

public class GameView {
    private final Canvas canvas;
    private final GameModel model;
    private final GraphicsContext graphics;

    public GameView(Canvas canvas, GameModel model) {
        this.canvas = canvas;
        this.model = model;
        this.graphics = canvas.getGraphicsContext2D();
    }

    public void showLost() {
        graphics.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        graphics.setFont(new Font(null, 125));
        graphics.setTextAlign(TextAlignment.CENTER);
        graphics.setTextBaseline(VPos.CENTER);
        graphics.setStroke(Color.WHITE);
        graphics.setLineWidth(7);
        graphics.setFill(Color.RED);
        graphics.strokeText("IT NEVER BEGAN", canvas.getWidth() / 2, canvas.getHeight() / 2);
        graphics.fillText("IT NEVER BEGAN", canvas.getWidth() / 2, canvas.getHeight() / 2);
    }

    public void render() {
        graphics.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        graphics.setFill(Color.RED);
        Boolean change = false;
        LinkedList<Point> currentBody = model.getSnake().getBody();
        try {
            for (Point point : currentBody) {
                if (change == true) {
                    graphics.setFill(Color.WHITE);
                    change = false;
                } else {
                    graphics.setFill(Color.RED);
                    change = true;
                }
                graphics.fillRect(point.getX() * 20, point.getY() * 20, 20, 20);
            }
        } catch (Exception ignored) {

        }
        graphics.setFill(Color.MEDIUMVIOLETRED);
        for (Food food : model.getFoods()) {
            graphics.fillOval(food.getX() * 20, food.getY() * 20, 20, 20);
        }
        graphics.setFill(Color.WHITE);
        graphics.setFont(new Font(null, 20));
        graphics.fillText("PSL: " + model.snakeLengthProperty().get(), canvas.getWidth() - 120, 30);
    }
}