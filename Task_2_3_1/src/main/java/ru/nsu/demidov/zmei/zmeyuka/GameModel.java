package ru.nsu.demidov.zmei.zmeyuka;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;
import javafx.beans.property.IntegerProperty;

/**
 * GameModel class.
 */


public class GameModel {
    private boolean isItOver = false;
    private int width;
    private int height;
    private Snake snake;
    private List<Food> foods;
    private final int initialFoodCount = 5;

    /**
     * GameModel constructor.
     */

    public GameModel(int width, int height) {
        this.width = width;
        this.height = height;
        this.snake = new Snake(width / 2, height / 2);
        this.foods = new ArrayList<>();
        for (int i = 0; i < initialFoodCount; i++) {
            generateFood();
        }
    }

    /**
     * snakeLengthProperty method.
     */

    public IntegerProperty snakeLengthProperty() {
        return snake.lengthProperty().snakeLengthProperty();
    }

    /**
     * update method.
     */

    public void update() {
        snake.move();
        checkCollisions();
        checkFood();
    }

    /**
     * generateFood method.
     */

    private void generateFood() {
        Random rand = new Random();
        int x = rand.nextInt(width);
        int y = rand.nextInt(height);
        boolean overlapped = true;
        LinkedList<Point> currentBody = snake.getBody();
        while (overlapped == true) {
            overlapped = false;
            try {
                for (Point joint : currentBody) {
                    if (Objects.equals(joint, snake.getHead())) {
                        continue;
                    }
                    if (x == joint.x && y == joint.y) {
                        overlapped = true;
                        x = rand.nextInt(width);
                        y = rand.nextInt(height);
                        break;
                    }
                }
            } catch (Exception ignored) {
                ignored.toString();
            }
        }
        int value = rand.nextInt(1, 50);
        foods.add(new Food(x, y, value));
    }

    /**
     * checkFood method.
     */

    private void checkFood() {
        Point head = snake.getHead();
        Point neck = snake.getBody().get(1);
        for (Food food : foods) {
            if ((food.getAbscsissAxisCord() == head.x || food.getAbscsissAxisCord() == neck.x)
                && (food.getOrdinateAxisCord() == head.y || food.getOrdinateAxisCord() == neck.getY())) {
                for (int i = 0; i < food.getValue(); ++i) {
                    snake.grow();
                }
                foods.remove(food);
                generateFood();
                break;
            }
        }
    }

    /**
     * getSnake method.
     */

    public Snake getSnake() {
        return snake;
    }

    /**
     * getFoods method.
     */

    public List<Food> getFoods() {
        return foods;
    }

    /**
     * setFoods method.
     */

    void setFoods(LinkedList<Food> foods) {
        this.foods = foods;
    }

    /**
     * checkCollisions method.
     */

    private void checkCollisions() {
        Point head = snake.getHead();
        if (head.getX() < 0 || head.getX() >= width || head.getY() < 0 || head.getY() >= height) {
            isItOver = true;
        }
        LinkedList<Point> body = snake.getBody();
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i)) == true) {
                isItOver = true;
                break;
            }
        }
        if (isItOver == true) {
            System.out.println("It's actually over");
        }
    }

    /**
     * itsActuallyOverForYou method.
     */

    public boolean itsActuallyOverForYou() {
        return isItOver;
    }
}